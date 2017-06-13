package ba.sitandfit.korisnici.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.jsonwrappers.KorisnikSubmitJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.model.KorisnikSubmit;
import ba.sitandfit.korisnici.repository.KorisnikRepository;
import ba.sitandfit.korisnici.repository.KorisnikSubmitRepository;

@Service
public class KorisnikSubmitServiceImpl implements KorisnikSubmitService{

	@Autowired
	KorisnikSubmitRepository ksr;
	
	@Override
	public KorisnikSubmitJSONWrapper createKorisnikSubmit(KorisnikSubmit k) {
		if (k.getId() != null && ksr.findOne(k.getId()) != null)
			return new KorisnikSubmitJSONWrapper("Error", "KorisnikSubmit već postoji!", k);
		
		return new KorisnikSubmitJSONWrapper("Success", "", ksr.save(k));
	}

	@Override
	public KorisnikSubmitJSONWrapper getKorisnikSubmit(Long id) {
		KorisnikSubmit x = ksr.findOne(id);
		 
		 if( x != null)
			 return new KorisnikSubmitJSONWrapper("Success","", x);
		 else
			 return new KorisnikSubmitJSONWrapper("Error","Trazeni submit korisnika ne postoji", null);
	}

	@Override
	public KorisnikSubmitJSONWrapper updateSubmitKorisnik(Long id, KorisnikSubmit k) {
		
		return null;
	}

	@Override
	public Boolean deleteKorisnikSubmit(Long id) {
		
		return null;
	}

	@Override
	public KorisnikSubmitJSONWrapper getKorisnikSubmitByUserId(Long userId) {
		KorisnikSubmit k=ksr.findByUserId(userId);
		
		if(k==null) return new KorisnikSubmitJSONWrapper("Error","Trazeni submit korisnika ne postoji", null);
		return new KorisnikSubmitJSONWrapper("Success","", k);
	}

	@Override
	public KorisnikSubmitJSONWrapper getKorisnikSubmitByGenString(String GenString) {
		
		KorisnikSubmit k=ksr.findByGenString(GenString);
		
		if(k==null) return new KorisnikSubmitJSONWrapper("Error","Trazeni submit korisnika ne postoji", null);
		return new KorisnikSubmitJSONWrapper("Success","", k);
	}

	@Override
	public KorisnikSubmitJSONWrapper potvrdiKorisnikSubmit(KorisnikSubmit k) {
		
		k.setPotvrdjen(1);
		return new KorisnikSubmitJSONWrapper("Success","",ksr.save(k));
	}

	@Override
	public KorisnikSubmitJSONWrapper createKorisnikSubmitByValues(String gen, Long userId) {
	
		KorisnikSubmit ks= new KorisnikSubmit(gen,userId);
		return new KorisnikSubmitJSONWrapper("Success","",ksr.save(ks));
	}

	@Override
	public List<KorisnikSubmit> getKorisnikSubmits() {
		return ksr.findAll();
	}
	
	

	
}
