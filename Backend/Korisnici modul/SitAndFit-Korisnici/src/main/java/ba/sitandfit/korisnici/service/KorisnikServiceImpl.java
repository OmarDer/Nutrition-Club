package ba.sitandfit.korisnici.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;
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
		
		
		if (korisnikRepository.findOne(k.getId()) != null)
			return new KorisnikJSONWrapper("Greska! Korisnik već postoji!", k);
		
		return new KorisnikJSONWrapper("Success", korisnikRepository.save(k));
		
	}

	@Override
	public KorisnikJSONWrapper getKorisnik(Long id) {
		
		
		 Korisnik x = korisnikRepository.getOne(id);
		 
		 if( x != null)
			 return new KorisnikJSONWrapper("Success", x);
		 else
			 return new KorisnikJSONWrapper("Trazeni korisnik ne postoji", x);
		 
	}

	@Override
	public KorisnikJSONWrapper updateKorisnik(Korisnik k) {
		
		Korisnik x;
		
		if((x = korisnikRepository.findOne(k.getId())) == null)
			return new KorisnikJSONWrapper("Greska! Korisnik ne postoji!", null);
		
		
		x.setAktivan(k.getAktivan() != null ? k.getAktivan() : x.getAktivan());
		x.setAdresaPrebivalista(k.getAdresaPrebivalista() != null ? k.getAdresaPrebivalista() : x.getAdresaPrebivalista());
		x.setIme(k.getIme() != null ? k.getIme() : x.getIme());
		x.setPrezime(k.getPrezime() != null ? k.getPrezime() : x.getPrezime());
		x.setKontaktTelefon(k.getKontaktTelefon() != null ? k.getKontaktTelefon() : x.getKontaktTelefon());
		x.setUserName(k.getUserName() != null ? k.getUserName() : x.getUserName());
		x.setPassword(k.getPassword() != null ? k.getPassword() : x.getPassword());
		x.setRola(k.getRola() != null ? k.getRola() : x.getRola());
		x.setEmail(k.getEmail() != null ? k.getEmail() : x.getEmail());
		
		return new KorisnikJSONWrapper("Success", korisnikRepository.save(x));
		
		
	}

	@Override
	public Boolean deleteKorisnik(Korisnik k) {
		
		Korisnik x = null;
		
		if((x = korisnikRepository.findOne(k.getId()) ) == null)
			return false;
		
		x.setAktivan(false);
		
		korisnikRepository.save(x);
		
		return true;
		
	}

	@Override
	public List<Korisnik> getKorisnici() {
		
		return korisnikRepository.findAll();
	}

}
