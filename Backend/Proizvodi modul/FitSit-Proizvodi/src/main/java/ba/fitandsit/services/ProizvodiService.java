package ba.fitandsit.services;

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
	
	public List<Proizvodi> vratiSve()
	{
		return pr.findAll();
	}
	
	
	public Boolean obrisiProizvod(Proizvodi p)
	{
		if(pr.findOne(p.getProizvod_ID())==null)
		{
			return false;
		}
		else
		{
			pr.delete(p.getProizvod_ID());
			return true;
		}
	}
	
	public Proizvodi azurirajProizvod(Proizvodi p)
	{
		Proizvodi p1=new Proizvodi();
		if(pr.findOne(p.getProizvod_ID())!=null)
		{
			p1.setProizvod_ID(p.getProizvod_ID());
			p1.setNaziv_proizvoda(p.getNaziv_proizvoda());
			p1.setAktivan(p.getAktivan());
			p1.setAutor_ID(p.getAutor_ID());
			p1.setCijena(p.getCijena());
			p1.setOpis_proizvoda(p.getOpis_proizvoda());
			p1.setNarudzbeList(p.getNarudzbeList());
			p1.setProgramiList(p.getProgramiList());
			p1.setSlika(p.getSlika());
			pr.delete(p.getProizvod_ID());
			return pr.save(p1);
		}
		return pr.save(p);
	}
	
	public Proizvodi kreirajProizvod(Proizvodi p)
	{
		return pr.save(p);
	}

	public Proizvodi vratiProizvodPoID(long id) {
		return pr.findOne(id);
	}
	
	public List<Proizvodi> izlistajProizvodePoProgramu(String programName){
		return pr.findByProgramName(programName);
	}
	
	

}
