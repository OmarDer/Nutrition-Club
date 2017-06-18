package ba.sitandfit.korisnici.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.sitandfit.korisnici.communication.ProizvodiREST;
import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.jsonwrappers.RolaJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.model.Rola;
import ba.sitandfit.korisnici.repository.KorisnikRepository;
import ba.sitandfit.korisnici.repository.RolaRepository;

@Service
public class KorisnikServiceImpl implements KorisnikService {
	
	ProizvodiREST proizvodiREST;
	KorisnikRepository korisnikRepository;
	@Autowired
	RolaRepository rolaRepository;
	
	@Autowired
	EmailSendService emailService;
	
	@Autowired
	public void setProizvodiREST(ProizvodiREST proizvodiREST) {
		this.proizvodiREST = proizvodiREST;
	}

	@Autowired
	public void setKorisnikRepository(KorisnikRepository korisnikRepository) {
		this.korisnikRepository = korisnikRepository;
	}

	@Override
	public KorisnikJSONWrapper createKorisnik(Korisnik k) {
		
		
		if (k.getId() != null && korisnikRepository.findOne(k.getId()) != null)
			return new KorisnikJSONWrapper("Error", "Korisnik već postoji!", k);
		//Uslovi da se username bude unique i da ne moze korisnik sa istim mailom da se prijavi više puta
		if(k.getUserName()!=null && korisnikRepository.findByUserName(k.getUserName())!=null)
			return new KorisnikJSONWrapper("Error","Korisnik sa unesenim usernameom već postoji!",null);
		if(k.getEmail()!=null && korisnikRepository.findByEmail(k.getEmail())!=null)
			return new KorisnikJSONWrapper("Error","Korisnik sa unesenom mail adresom već postoji!",null);
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
		
		List<Korisnik> vraceni = (ArrayList<Korisnik>)korisnikRepository.findAll();
		
		/*List<Korisnik> zaVratit = new ArrayList<Korisnik>();
		
		for(Korisnik x : vraceni)
			if(x.getAktivan())
				zaVratit.add(x);
		*/
		return vraceni;
							
	}

	@Override
	public RolaJSONWrapper getRolaKorisnika(Long idKorisnika) {
		
		Korisnik x = null;
		
		if((x = korisnikRepository.findOne(idKorisnika)) == null)
			return new RolaJSONWrapper("Error","Korisnik ne postoji", null);
		
		
		return new RolaJSONWrapper("Success", "", x.getRola());
	}

	@Override
	public String getProgrameKorisnika(Long id) {
		return proizvodiREST.getProgramiByKorisnikId(id);
	}

	@Override
	public KorisnikJSONWrapper getKorisnikByUserName(String userName) {
		
		Korisnik x;
		if((x = korisnikRepository.findByUserName(userName)) == null)
			return new KorisnikJSONWrapper("Error","Korisnik ne postoji", null);
		
		return new KorisnikJSONWrapper("Success","", x);
	}

	@Override
	public KorisnikJSONWrapper aktivirajKorisnika(Long id) {
		
		String rola="ROLE_USER";
		Korisnik kor=korisnikRepository.findOne(id);
		kor.setAktivan(true);
		Rola r=rolaRepository.findByNazivRole(rola);
		kor.setRola(r);
		return new KorisnikJSONWrapper("Success","",korisnikRepository.save(kor));
	}

	@Override
	public KorisnikJSONWrapper odobriRegistrovanogKorisnika(Long id) {
		
		Korisnik kor=korisnikRepository.findOne(id);
		try {
			emailService.sendEmail(id);
		} 
		catch (UnsupportedEncodingException e) 
		{	
			return new KorisnikJSONWrapper("Error", "Slanje verifikacijskog maila nije uspjesno!", null); 
		}
		kor.setOdobren(1);
		return new KorisnikJSONWrapper("Success", "", korisnikRepository.save(kor));
	}
	
	@Override
	public KorisnikJSONWrapper zabraniRegistrovanogKorisnika(Long id) {
		
		Korisnik kor=korisnikRepository.findOne(id);
		kor.setOdobren(0);
		return new KorisnikJSONWrapper("Success", "", korisnikRepository.save(kor));
	}

	@Override
	public KorisnikJSONWrapper dodajRoluKorisniku(Long idk, Long idr) {
		
		Korisnik k = korisnikRepository.findOne(idk);
		Rola r = rolaRepository.findOne(idr);
		
		if(k==null) return new KorisnikJSONWrapper("Error","Nepostojeći korisnik!",null);
		if(r==null) return new KorisnikJSONWrapper("Error","Nepostojeća rola!",null);
		
		k.setRola(r);
		return new KorisnikJSONWrapper("Success", "", korisnikRepository.save(k));
		
	}

}
