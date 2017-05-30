package ba.fitandsit.services;
import ba.fitandsit.wrappers.*;
import ba.fitandsit.repository.*;
import ba.fitandsit.security.TokenAuthenticationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Programi;
import ba.fitandsit.model.Proizvodi;
import ba.fitandsit.repository.*;

@Service
public class NarudzbeService {
	
	@Autowired
	private NarudzbeRepository nr;

	@Autowired
	private ProizvodiRepository pr;
	
	@Autowired
	private UsersCommunicationService ucm;
	
	public List<Narudzbe> vratiSve()
	{
		return nr.findAll();
	}
	
	public List<Narudzbe> vratiSveAktivne()
	{
		return nr.findAktivne();
	}
	public JsonWrapperNarudzbe vratiNarudzbuPoID(Long id)
	{
		Narudzbe n=nr.findOne(id);
		if(n==null)return new JsonWrapperNarudzbe("Error","Unesena narudzba ne postoji!",null);
		return new JsonWrapperNarudzbe("Success","",n);
	}
	
	public Boolean obrisiNarudzbu(Long id)
	{
		Narudzbe n=nr.findOne(id);
		if(n==null || n.getAktivan()==0)
		{
			return false;
		}
		else
		{
			n.setAktivan(0);
			nr.save(n);
			return true;
		}
	}
	
	public JsonWrapperNarudzbe azurirajNarudzbu(Long id, Narudzbe n,String token)
	{
		Narudzbe n1=new Narudzbe();
		Narudzbe sel=nr.findOne(id);
		
		if(sel!=null)
		{
			n1.setNarudzbaID((n.getNarudzbaID()!=null)? n.getNarudzbaID():sel.getNarudzbaID());
			n1.setAktivan((n.getAktivan()!=null)? n.getAktivan():sel.getAktivan());
			n1.setDatum((n.getDatum()!=null)? n.getDatum():sel.getDatum());
			
			if(n.getKorisnikID()!=null && ucm.provjeriKorisnika(n.getKorisnikID())==false)
			{
				return new JsonWrapperNarudzbe("Error","Uneseni korisnik ne postoji!",null);
			}
			n1.setKorisnikID((n.getKorisnikID()!=null)? n.getKorisnikID():sel.getKorisnikID());
			
			if(n.getProdavacID()!=null && ucm.provjeriKorisnika(n.getProdavacID())==false)
			{
				return new JsonWrapperNarudzbe("Error","Uneseni prodavac ne postoji!",null);
			}
			n1.setProdavacID((n.getProdavacID()!=null)? n.getProdavacID():sel.getProdavacID());
			n1.setProizvodiList((n.getProizvodiList()!=null)? n.getProizvodiList():sel.getProizvodiList());
			
			return new JsonWrapperNarudzbe("Success","",nr.save(n1));
		}
		
	 return new JsonWrapperNarudzbe("Error","Unesena narudzba ne postoji!",null);
	}
	
	public JsonWrapperNarudzbe kreirajNarudzbu(Narudzbe n, String token)
	{
		String userId=TokenAuthenticationService.vratiIdKorisnika(token);
		if(n.getKorisnikID()!=null && ucm.provjeriKorisnika(n.getKorisnikID())==false)
		{
			return new JsonWrapperNarudzbe("Error","Uneseni korisnik ne postoji!",null);
		}
		if(n.getProdavacID()!=null && ucm.provjeriKorisnika(n.getProdavacID())==false)
		{
			return new JsonWrapperNarudzbe("Error","Uneseni prodavac ne postoji!",null);
		}
		if(n.getNarudzbaID()!=null&& nr.findOne(n.getNarudzbaID())==null) return new JsonWrapperNarudzbe("Success","",nr.save(n));
		return new JsonWrapperNarudzbe("Error","Unesena narudzba vec postoji!",null);
	}

	public JsonWrapperListNarudzbe izlistajNarudzbuZaKupca(Long id,String token)
	{
		if(ucm.provjeriKorisnika(id)==false) return new JsonWrapperListNarudzbe("Error","Uneseni korisnik ne postoji!",null); 
			
		return new JsonWrapperListNarudzbe("Success","",nr.findBykorisnikID(id));
	}
	
	public JsonWrapperListNarudzbe izlistajNarudzbuZaProdavaca(Long id, String token)
	{
		if(ucm.provjeriKorisnika(id)==false) return new JsonWrapperListNarudzbe("Error","Uneseni prodavac ne postoji!",null); 
		
		return new JsonWrapperListNarudzbe("Success","",nr.findByprodavacID(id));
	}

	public JsonWrapperNarudzbe dodajProizvodeUNarudzbu(Long idn,Long idp)
	{
		Narudzbe n=nr.findOne(idn);
		Proizvodi p=pr.findOne(idp);
		
		if(n==null && p==null)
		{
			return new JsonWrapperNarudzbe("Error","Unesena narudzba i proizvod ne postoje!",null);
		}
		else if(p==null)
		{
			return new JsonWrapperNarudzbe("Error","Uneseni proizvod ne postoji!",null);
		}
		else if(n==null)
		{
			return new JsonWrapperNarudzbe("Error","Unesena narudzba ne postoji!",null);
		}
		
		n.getProizvodiList().add(p);
		return new JsonWrapperNarudzbe("Success","",nr.save(n));
	}
	
	public JsonWrapperNarudzbe obrisiProizvodIzNarudzbe(Long idn,Long idp)
	{
		Narudzbe n=nr.findOne(idn);
		Proizvodi p=pr.findOne(idp);
		
		if(n==null && p==null)
		{
			return new JsonWrapperNarudzbe("Error","Unesena narudzba i proizvod ne postoje!",null);
		}
		else if(p==null)
		{
			return new JsonWrapperNarudzbe("Error","Uneseni proizvod ne postoji!",null);
		}
		else if(n==null)
		{
			return new JsonWrapperNarudzbe("Error","Unesena narudzba ne postoji!",null);
		}
		if(n.getAktivan()==0) return new JsonWrapperNarudzbe("Error","Unesena narudzba ne postoji!",null);
		if(p.getAktivan()==0) return new JsonWrapperNarudzbe("Error","Uneseni proizvod ne postoji!",null); 
		n.getProizvodiList().remove(p);
		return new JsonWrapperNarudzbe("Success","",nr.save(n));
	}
	public JsonWrapperListProizvodi vratiProizvodeZaNarudzbu(Long nid)
	{
		Narudzbe n=nr.findOne(nid);
		if(n==null)
		{
			return new JsonWrapperListProizvodi("Error","Unesena narudzba ne postoji!",null);
		}
		return new JsonWrapperListProizvodi("Succes","",nr.findProizvodi(nid));
	}
}
