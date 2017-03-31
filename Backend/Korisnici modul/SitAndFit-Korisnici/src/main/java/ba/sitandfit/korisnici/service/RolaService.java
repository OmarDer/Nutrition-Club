package ba.sitandfit.korisnici.service;

import java.util.List;

import ba.sitandfit.korisnici.jsonwrappers.RolaJSONWrapper;
import ba.sitandfit.korisnici.model.Rola;

public interface RolaService {
	
	RolaJSONWrapper createRola(Rola k);
	RolaJSONWrapper getRola(Long id);
	RolaJSONWrapper updateRola(Long id, Rola k);
	Boolean deleteRola(Long id);
	List<Rola> getRole();

}
