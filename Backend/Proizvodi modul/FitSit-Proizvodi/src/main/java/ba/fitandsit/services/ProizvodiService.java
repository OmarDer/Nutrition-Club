package ba.fitandsit.services;
import ba.fitandsit.wrappers.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.fitandsit.model.Programi;
import ba.fitandsit.model.Proizvodi;
import ba.fitandsit.repository.*;

@Service
public class ProizvodiService {
	
	@Autowired
	private ProizvodiRepository pr;
	
	@Autowired
	private UsersCommunicationService ucm;
	
	@Autowired
	private ProgramiRepository progRep; 
	
	public List<Proizvodi> vratiSve()
	{
		return pr.findAll();
	}
	
	public List<Proizvodi> vratiAktivne()
	{
		return pr.findAktivne();
	}
	public Boolean obrisiProizvod(Long id)
	{
		Proizvodi p=pr.findOne(id);
		if(p==null || p.getAktivan()==0)
		{
			return false;
		}
		else
		{
			p.setAktivan(0);
			pr.save(p);
			return true;
		}
	}
	
	public JsonWrapperProizvodi azurirajProizvod(Long id,Proizvodi p,String token)
	{
		Proizvodi p1=new Proizvodi();
		Proizvodi sel=pr.findOne(id);
		if(pr.findOne(p.getProizvod_ID())!=null)
		{
			p1.setProizvod_ID((p.getProizvod_ID()!=null)?p.getProizvod_ID():sel.getProizvod_ID());
			p1.setNaziv_proizvoda((p.getNaziv_proizvoda()!=null)?p.getNaziv_proizvoda():sel.getNaziv_proizvoda());
			p1.setAktivan((p.getAktivan()!=null)?p.getAktivan():sel.getAktivan());
			if(p.getAutor_ID()!=null && ucm.provjeriKorisnika(p.getAutor_ID(),token)==false)
			{
				return new JsonWrapperProizvodi("Error","Uneseni kreator proizvoda ne postoji!",null);
			}
			p1.setAutor_ID((p.getAutor_ID()!=null)?p.getAutor_ID():sel.getAutor_ID());
			p1.setCijena((p.getCijena()!=null)?p.getCijena():sel.getCijena());
			p1.setOpis_proizvoda((p.getOpis_proizvoda()!=null)?p.getOpis_proizvoda():sel.getOpis_proizvoda());
			p1.setNarudzbeList(p.getNarudzbeList());
			p1.setProgramiList(p.getProgramiList());
			p1.setSlika((p.getSlika()!=null)?p.getSlika():sel.getSlika());
			
			return new JsonWrapperProizvodi("Success","",pr.save(p1));
		}
		return new JsonWrapperProizvodi("Error","Uneseni proizvod ne postoji!",null);
	}
	
	public JsonWrapperProizvodi kreirajProizvod(Proizvodi p, String token)
	{
		if(p.getAutor_ID()!=null && ucm.provjeriKorisnika(p.getAutor_ID(),token)==false)
		{
			return new JsonWrapperProizvodi("Error","Uneseni kreator proizvoda ne postoji!",null);
		}
		if(p.getProizvod_ID()!=null&&pr.findOne(p.getProizvod_ID())==null) return new JsonWrapperProizvodi("Success","",pr.save(p));
		return new JsonWrapperProizvodi("Error","Uneseni proizvod vec postoji!",null);
	}

	public JsonWrapperProizvodi vratiProizvodPoID(Long id) {
		Proizvodi p=pr.findOne(id);
		if(p==null)return new JsonWrapperProizvodi("Error","Uneseni proizvod ne postoji!",null);
		return new JsonWrapperProizvodi("Success","",p);
	}
	
	public JsonWrapperListProizvodi izlistajProizvodePoProgramu(String programName){
		
		if(progRep.findBynaziv_programa(programName)==null)
		{
			return new JsonWrapperListProizvodi("Error","Program sa unesenim imenom ne postoji!");
		}
		
		return new JsonWrapperListProizvodi("Success","",pr.findByProgramName(programName));
	}
	
	

}
