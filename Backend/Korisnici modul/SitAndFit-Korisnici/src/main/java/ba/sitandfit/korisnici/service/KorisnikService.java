package ba.sitandfit.korisnici.service;

import java.util.List;

import ba.sitandfit.korisnici.model.Korisnik;

public interface KorisnikService {
	
	Korisnik createKorisnik(Korisnik k);
	Korisnik getKorisnik(Long id);
	Korisnik updateKorisnik(Korisnik k);
	Boolean deleteKorisnik(Korisnik k);
	List<Korisnik> getKorisnici();

}
