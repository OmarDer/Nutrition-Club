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
	String getProgrameKorisnika(Long id);
	KorisnikJSONWrapper getKorisnikByUserName(String userName);
	KorisnikJSONWrapper aktivirajKorisnika(Long id);
	KorisnikJSONWrapper odobriRegistrovanogKorisnika(Long id);
	KorisnikJSONWrapper dodajRoluKorisniku(Long idk,Long idr);
	KorisnikJSONWrapper zabraniRegistrovanogKorisnika(Long id);

}
