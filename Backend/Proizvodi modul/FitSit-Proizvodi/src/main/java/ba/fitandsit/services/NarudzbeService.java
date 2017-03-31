package ba.fitandsit.services;
import ba.fitandsit.repository.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Proizvodi;
import ba.fitandsit.repository.*;

@Service
public class NarudzbeService {
	
	@Autowired
	private NarudzbeRepository nr;

	public List<Narudzbe> vratiSve()
	{
		return nr.findAll();
	}
	
	public Narudzbe vratiNarudzbuPoID(long id)
	{
		return nr.findOne(id);
	}
	
	public Boolean obrisiNarudzbu(Narudzbe n)
	{
		if(nr.findOne(n.getNarudzbaID())==null)
		{
			return false;
		}
		else
		{
			nr.delete(n.getNarudzbaID());
			return true;
		}
	}
	
	public Narudzbe azurirajNarudzbu(Narudzbe n)
	{
		Narudzbe n1=new Narudzbe();
		
		if(nr.findOne(n.getNarudzbaID())!=null)
		{
			n1.setNarudzbaID(n.getNarudzbaID());
			n1.setAktivan(n.getAktivan());
			n1.setDatum(n.getDatum());
			n1.setKorisnikID(n.getKorisnikID());
			n1.setProdavacID(n.getProdavacID());
			n1.setProizvodiList(n.getProizvodiList());
			
			nr.delete(n.getNarudzbaID());
			return nr.save(n1);
		}
		
		return nr.save(n);
	}
	
	public Narudzbe kreirajNarudzbu(Narudzbe n)
	{
		return nr.save(n);
	}

	public List<Narudzbe> izlistajNarudzbuZaKupca(long id)
	{
		return nr.findBykorisnikID(id);
	}

}
