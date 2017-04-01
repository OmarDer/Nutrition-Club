package ba.sitandfit.korisnici.service;

import java.util.List;

import ba.sitandfit.korisnici.jsonwrappers.PretplatnikJSONWrapper;
import ba.sitandfit.korisnici.model.Pretplatnik;

public interface PretplatnikService {
	
	PretplatnikJSONWrapper createPretplatnik(Pretplatnik p);
	PretplatnikJSONWrapper getPretplatnik(Long id);
	PretplatnikJSONWrapper updatePretplatnik(Long id, Pretplatnik p);
	Boolean deletePretplatnik(Long id);
	List<Pretplatnik> getPretplatnici();

}
