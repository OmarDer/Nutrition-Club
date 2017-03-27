package ba.sitandfit.korisnici.service;

import java.util.List;

import ba.sitandfit.korisnici.model.Rola;

public interface RolaService {
	
	Rola createRola(Rola k);
	Rola getRola(Long id);
	Rola updateRola(Rola k);
	Boolean deleteRola(Rola k);
	List<Rola> getRole();

}
