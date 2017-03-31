package ba.sitandfit.korisnici.service;

import java.util.List;

import ba.sitandfit.korisnici.jsonwrappers.StanjeJSONWrapper;
import ba.sitandfit.korisnici.model.Stanje;

public interface StanjeService {
	
	
	StanjeJSONWrapper createStanje(Stanje s);
	StanjeJSONWrapper getStanje(Long id);
	StanjeJSONWrapper updateStanje(Long id, Stanje s);
	Boolean deleteStanje(Long id);
	List<Stanje> getStanja();
	
	List<Stanje> getStanjaKorisnika(Long korisnikId);

}
