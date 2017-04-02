package ba.sitandfit.korisnici.service;

import java.util.List;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.jsonwrappers.RolaJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;

public interface KorisnikService {
	
	KorisnikJSONWrapper createKorisnik(Korisnik k);
	KorisnikJSONWrapper getKorisnik(Long id);
	KorisnikJSONWrapper updateKorisnik(Long id, Korisnik k);
	Boolean deleteKorisnik(Long id);
	List<Korisnik> getKorisnici();
	RolaJSONWrapper getRolaKorisnika(Long idKorisnika);

}
