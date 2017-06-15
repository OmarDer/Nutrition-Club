package com.fitandsit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitandsit.Model.komentari;
import com.fitandsit.Repository.KomentariRepo;
import com.fitandsit.jsonwrappers.KomentariJSONWrapper;
import com.fitandsit.jsonwrappers.KomentariListJSONWrapper;
import com.fitandsit.jsonwrappers.VijestiJSONWrapper;

@Service
public class komentariService implements komentariInterface {

	KomentariRepo komentarrepo;
	
	@Autowired
	private KorisniciCommunication kc;
	
	@Autowired
	public void setKomentarRepo(KomentariRepo komentarrepo) {
		this.komentarrepo = komentarrepo;
	}
	
	@Override
	public KomentariJSONWrapper createKomentar(komentari k){
		if (k.getKomentarID()!=null && komentarrepo.findOne(k.getKomentarID()) != null)
			return new KomentariJSONWrapper("Error", "Komentar već postoji!", k);
		
		if(k.getAutorID()!=null && kc.checkKorisnik(k.getAutorID())==false)
		{
			return new KomentariJSONWrapper("Error","Uneseni korisnik ne postoji!",null);
		}
		
		return new KomentariJSONWrapper("Success", "", komentarrepo.save(k));
	}
	
	@Override
	public KomentariJSONWrapper getKomentar(Long id){
		komentari k =komentarrepo.findOne(id);
		 
		 if(k != null)
			 return new KomentariJSONWrapper("Success","", k);
		 else
			 return new KomentariJSONWrapper("Error","Trazeni komentar ne postoji", null);
	}
	
	@Override
	public KomentariJSONWrapper updateKomentar(Long id, komentari k){
		komentari x;
		
		if((x = komentarrepo.findOne(id)) == null)
			return new KomentariJSONWrapper("Error","Komentar ne postoji!", null);
		
		if(k.getAutorID()!=null && kc.checkKorisnik(x.getAutorID())==false)
		{
			return new KomentariJSONWrapper("Error","Uneseni korisnik ne postoji!",null);
		}
		
		x.setAutorID(k.getAutorID() != null ? k.getAutorID() : x.getAutorID());
		x.setImaRoditelja(k.getImaRoditelja() != null ? k.getImaRoditelja() : x.getImaRoditelja());
		x.setTextKomentara(k.getTextKomentara() != null ? k.getTextKomentara() : x.getTextKomentara());
		x.setAktivan(k.getAktivan() != null ? k.getAktivan() : x.getAktivan());

		return new KomentariJSONWrapper("Success","", komentarrepo.save(x));
	}
	
	@Override
	public Boolean deleteKomentar(Long id){
		komentari x = null;
		
		if((x = komentarrepo.findOne(id) ) == null)
			return false;
		
		x.setAktivan(0);
		
		komentarrepo.save(x);
		
		return true;
	}
	
	@Override
	public List<komentari> getKomentari(){
		return komentarrepo.findAll();
	}
	
	@Override
	public List<komentari> getAktivKomentari(){
		return komentarrepo.findAktivne();
	}
	
	@Override
	public VijestiJSONWrapper getvijesti(Long id){
komentari x = null;
		
		if((x = komentarrepo.findOne(id)) == null)
			return new VijestiJSONWrapper("Error","Vijest ne postoji", null);
		
		
		return new VijestiJSONWrapper("Success", "", x.getVijesti());
	}
	
	@Override
	public KomentariJSONWrapper getchildkomentar(Long id){
		komentari x = null;
		
		if((x = komentarrepo.findOne(id)) == null)
			return new KomentariJSONWrapper("Error","Komentar ne postoji", null);
		
		
		return new KomentariJSONWrapper("Success", "", x.getRoditelj());
	}
	
	public KomentariListJSONWrapper getKomentarKorisnik(Long id)
	{
		if(kc.checkKorisnik(id)==false)
		{
			return new KomentariListJSONWrapper("Error","Uneseni korisnik ne postoji!",null);
		}
		
		List<komentari> k=komentarrepo.findByAutorID(id);
		if(k==null)
		{
			return new KomentariListJSONWrapper("Success","Korisnik nije komentarisao ni jednu vijest!",null);
		}
		return new KomentariListJSONWrapper("Success","",k);
		
	}
	
}
