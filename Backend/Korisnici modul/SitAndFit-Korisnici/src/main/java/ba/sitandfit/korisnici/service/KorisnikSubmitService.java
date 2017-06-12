package ba.sitandfit.korisnici.service;

import java.util.List;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikSubmitJSONWrapper;
import ba.sitandfit.korisnici.model.KorisnikSubmit;


public interface KorisnikSubmitService {
	KorisnikSubmitJSONWrapper createKorisnikSubmit(KorisnikSubmit k);
	KorisnikSubmitJSONWrapper createKorisnikSubmitByValues(String gen,Long userId);
	KorisnikSubmitJSONWrapper getKorisnikSubmit(Long id);
	KorisnikSubmitJSONWrapper updateSubmitKorisnik(Long id, KorisnikSubmit k);
	Boolean deleteKorisnikSubmit(Long id);
	KorisnikSubmitJSONWrapper getKorisnikSubmitByUserId(Long userId);
	KorisnikSubmitJSONWrapper getKorisnikSubmitByGenString(String GenString);
	KorisnikSubmitJSONWrapper potvrdiKorisnikSubmit(KorisnikSubmit k);
	List<KorisnikSubmit> getKorisnikSubmits();
}
