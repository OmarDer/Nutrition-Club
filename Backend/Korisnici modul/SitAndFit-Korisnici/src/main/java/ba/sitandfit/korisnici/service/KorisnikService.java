package ba.sitandfit.korisnici.service;

import java.util.List;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;

public interface KorisnikService {
	
	KorisnikJSONWrapper createKorisnik(Korisnik k);
	KorisnikJSONWrapper getKorisnik(Long id);
	KorisnikJSONWrapper updateKorisnik(Korisnik k);
	Boolean deleteKorisnik(Korisnik k);
	List<Korisnik> getKorisnici();

}
