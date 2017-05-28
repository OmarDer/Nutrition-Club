package ba.fitandsit.services;
import ba.fitandsit.wrappers.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Programi;
import ba.fitandsit.model.Proizvodi;
import ba.fitandsit.repository.*;
import ba.fitandsit.security.TokenAuthenticationService;

@Service
public class ProgramiService {

	@Autowired
	private ProgramiRepository pr;
	
	@Autowired
	private ProizvodiRepository proz;
	
	@Autowired
	private UsersCommunicationService ucm;
	
	public List<Programi> vratiSve()
	{
		return pr.findAll();
	}
	
	public List<Programi> vratiSaKorisnikom()
	{
		return pr.findProgrameSaKorisnikom();
	}
	
	public List<Programi>vratiSaKorisnikomSve()
	{
		return pr.findProgrameSaKorisnikomSve();
	}
	
	public List<Programi>vratiAktivne()
	{
		return pr.findAktivne();
	}
	
	public JsonWrapperProgrami vratiProgramPoID(Long id)
	{
		Programi p=pr.findOne(id);
		if(p==null)return new JsonWrapperProgrami("Error","Uneseni program ne postoji!",null);
		return new JsonWrapperProgrami("Success","",p);
	}
	
	public Boolean obrisiProgram(Long id)
	{
		Programi p=pr.findOne(id);
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
	
	public JsonWrapperProgrami azurirajProgram(Long id,Programi p,String token)
	{
		Programi p1=new Programi();
		Programi sel=pr.findOne(id);
		
		if(sel!=null)
		{
			p1.setProgramID((p.getProgramID()!=null) ? p.getProgramID():sel.getProgramID());
			p1.setNaziv_programa((p.getNaziv_programa()!=null) ? p.getNaziv_programa():sel.getNaziv_programa());
			if(p.getAutorID()!=null && ucm.provjeriKorisnika(p.getAutorID(),token)==false)
			{
				return new JsonWrapperProgrami("Error","Program se azurira sa nepostojecim korisnikom!",null);
				
			}
			p1.setAutorID((p.getAutorID()!=null) ? p.getAutorID():sel.getAutorID());
			p1.setOpis_programa((p.getOpis_programa()!=null) ? p.getOpis_programa():sel.getOpis_programa());
			p1.setAktivan((p.getAktivan()!=null) ? p.getAktivan():sel.getAktivan());
			p1.setProizvodiList((p.getProizvodiList()!=null) ? p.getProizvodiList():sel.getProizvodiList());
			
			return new JsonWrapperProgrami("Success","",pr.save(p1));
		}
		
		return new JsonWrapperProgrami("Error","Uneseni program ne postoji!",null);
	}
	
	public JsonWrapperProgrami kreirajProgram(Programi p, String token)
	{
		if(p.getAutorID()!=null && ucm.provjeriKorisnika(p.getAutorID(),token)==false)
		{
			return new JsonWrapperProgrami("Error","Uneseni korisnik ne postoji!",null);
		}
		if(p.getProgramID()!=null && pr.findOne(p.getProgramID())==null) return new JsonWrapperProgrami("Success","",pr.save(p));
		return new JsonWrapperProgrami("Error","Uneseni program vec postoji!",null);
	}

	public JsonWrapperProgrami izlistajProgramPoImenu(String name) {
		Programi p=pr.findBynaziv_programa(name);
		if(p!=null) return new JsonWrapperProgrami("Success","",p);
		return new JsonWrapperProgrami("Error","Trazeni program ne postoji!",null);
		
	}
	
	public JsonWrapperProgrami dodajProizvodeUProgram(Long id,Long idp){
		Programi ps=pr.findOne(id);
		Proizvodi p=proz.findOne(idp);
		
		if(p==null && ps==null)
		{
			return new JsonWrapperProgrami("Error","Uneseni program i proizvod ne postoji!",null);
		}
		else if(p==null)
		{
			return new JsonWrapperProgrami("Error","Uneseni proizvod ne postoji!",null);
		}
		else if(ps==null)
		{
			return new JsonWrapperProgrami("Error","Uneseni program ne postoji!",null);
		}
		if(p.getAktivan()==0) return new JsonWrapperProgrami("Error","Uneseni proizvod ne postoji!",null);
		if(ps.getAktivan()==0) return new JsonWrapperProgrami("Error","Uneseni program ne postoji!",null);
		ps.getProizvodiList().add(p);
		return new JsonWrapperProgrami("Success","",pr.save(ps));
	}
	
	public JsonWrapperProgrami obrisiProizvodeIzPrograma(Long idp, Long idpr)
	{
		Programi ps=pr.findOne(idp);
		Proizvodi p=proz.findOne(idpr);
		
		if(p==null && ps==null)
		{
			return new JsonWrapperProgrami("Error","Uneseni program i proizvod ne postoji!",null);
		}
		else if(p==null)
		{
			return new JsonWrapperProgrami("Error","Uneseni proizvod ne postoji!",null);
		}
		else if(ps==null)
		{
			return new JsonWrapperProgrami("Error","Uneseni program ne postoji!",null);
		}
		if(p.getAktivan()==0) return new JsonWrapperProgrami("Error","Uneseni proizvod ne postoji!",null);
		if(ps.getAktivan()==0) return new JsonWrapperProgrami("Error","Uneseni program ne postoji!",null);
		
		ps.getProizvodiList().remove(p);
		return new JsonWrapperProgrami("Success","",pr.save(ps));
	}
	
	public JsonWrapperProgrami vratiProgramZaKorisnika(Long id,String token)
	{
		if(ucm.provjeriKorisnika(id,token)==false)
		{
			return new JsonWrapperProgrami("Error","Uneseni korisnik ne postoji!",null);
		}
		
		Programi p=pr.findByUser(id);
		if(p==null)
		{
			return new JsonWrapperProgrami("Success","Korisnik ne konzumira niti jedan program!",null);
		}
		return new JsonWrapperProgrami("Success","",p);
		
	}
}
