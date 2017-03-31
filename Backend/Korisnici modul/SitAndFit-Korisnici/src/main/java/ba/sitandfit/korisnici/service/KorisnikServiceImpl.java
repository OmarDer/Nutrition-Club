package ba.sitandfit.korisnici.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.jsonwrappers.RolaJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.model.Rola;
import ba.sitandfit.korisnici.repository.KorisnikRepository;

@Service
public class KorisnikServiceImpl implements KorisnikService {
	

	KorisnikRepository korisnikRepository;
	
	@Autowired
	public void setKorisnikRepository(KorisnikRepository korisnikRepository) {
		this.korisnikRepository = korisnikRepository;
	}

	@Override
	public KorisnikJSONWrapper createKorisnik(Korisnik k) {
		
		
		if (k.getId() != null && korisnikRepository.findOne(k.getId()) != null)
			return new KorisnikJSONWrapper("Error", "Korisnik već postoji!", k);
		
		return new KorisnikJSONWrapper("Success", "", korisnikRepository.save(k));
		
	}

	@Override
	public KorisnikJSONWrapper getKorisnik(Long id) {
		
		
		 Korisnik x = korisnikRepository.findOne(id);
		 
		 if( x != null)
			 return new KorisnikJSONWrapper("Success","", x);
		 else
			 return new KorisnikJSONWrapper("Error","Trazeni korisnik ne postoji", null);
		 
	}

	@Override
	public KorisnikJSONWrapper updateKorisnik(Long idKorisnika, Korisnik k) {
		
		Korisnik x;
		
		if((x = korisnikRepository.findOne(idKorisnika)) == null)
			return new KorisnikJSONWrapper("Error","Korisnik ne postoji!", null);
		
		
		x.setAktivan(k.getAktivan() != null ? k.getAktivan() : x.getAktivan());
		x.setAdresaPrebivalista(k.getAdresaPrebivalista() != null ? k.getAdresaPrebivalista() : x.getAdresaPrebivalista());
		x.setIme(k.getIme() != null ? k.getIme() : x.getIme());
		x.setPrezime(k.getPrezime() != null ? k.getPrezime() : x.getPrezime());
		x.setKontaktTelefon(k.getKontaktTelefon() != null ? k.getKontaktTelefon() : x.getKontaktTelefon());
		x.setUserName(k.getUserName() != null ? k.getUserName() : x.getUserName());
		x.setPassword(k.getPassword() != null ? k.getPassword() : x.getPassword());
		//x.setRola(k.getRola() != null ? k.getRola() : x.getRola());
		x.setEmail(k.getEmail() != null ? k.getEmail() : x.getEmail());
		
		return new KorisnikJSONWrapper("Success","", korisnikRepository.save(x));
		
		
	}

	@Override
	public Boolean deleteKorisnik(Long idKorisnika) {
		
		Korisnik x = null;
		
		if((x = korisnikRepository.findOne(idKorisnika) ) == null)
			return false;
		
		x.setAktivan(false);
		
		korisnikRepository.save(x);
		
		return true;
		
	}

	@Override
	public List<Korisnik> getKorisnici() {
		
		return korisnikRepository.findAll();
	}

	@Override
	public RolaJSONWrapper getRolaKorisnika(Long idKorisnika) {
		
		Korisnik x = null;
		
		if((x = korisnikRepository.findOne(idKorisnika)) == null)
			return new RolaJSONWrapper("Error","Korisnik ne postoji", null);
		
		
		return new RolaJSONWrapper("Success", "", x.getRola());
	}

}
