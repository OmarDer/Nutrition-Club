package ba.fitandsit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.fitandsit.model.Programi;
import ba.fitandsit.model.Proizvodi;
import ba.fitandsit.repository.*;

@Service
public class ProgramiService {

	@Autowired
	private ProgramiRepository pr;
	
	
	public List<Programi> vratiSve()
	{
		return pr.findAll();
	}
	
	public Programi vratiProgramPoID(long id)
	{
		return pr.findOne(id);
	}
	
	public Boolean obrisiProgram(Programi p)
	{
		if(pr.findOne(p.getProgramID())==null)
		{
			return false;
		}
		else
		{
			pr.delete(p.getProgramID());
			return true;
		}
	}
	
	public Programi azurirajProgram(Programi p)
	{
		Programi p1=new Programi();
		
		if(pr.findOne(p.getProgramID())!=null)
		{
			p1.setProgramID(p.getProgramID());
			p1.setNaziv_programa(p.getNaziv_programa());
			p1.setAutorID(p.getAutorID());
			p1.setOpis_programa(p.getOpis_programa());
			p1.setAktivan(p.getAktivan());
			p1.setProizvodiList(p.getProizvodiList());
			pr.delete(p.getProgramID());
			return pr.save(p1);
		}
		
		return pr.save(p);
	}
	
	public Programi kreirajProgram(Programi p)
	{
		return pr.save(p);
	}

	public Programi izlistajProgramPoImenu(String name) {
		return pr.findBynaziv_programa(name);
	}
	
	public Programi dodajProizvodeUProgram(long id,Proizvodi p){
		Programi ps=pr.findOne(id);
		ps.getProizvodiList().add(p);
		return pr.save(ps);
	}
}
