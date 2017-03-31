package ba.sitandfit.korisnici.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.sitandfit.korisnici.jsonwrappers.RolaJSONWrapper;
import ba.sitandfit.korisnici.model.Rola;
import ba.sitandfit.korisnici.repository.RolaRepository;

@Service
public class RolaServiceImpl implements RolaService{

	RolaRepository rolaRepository;

	@Autowired
	public void setRolaRepository(RolaRepository rolaRepository) {
		this.rolaRepository = rolaRepository;
	}

	@Override
	public RolaJSONWrapper createRola(Rola r) {
		
		if(r.getId() != null && rolaRepository.findOne(r.getId()) != null)
			return new RolaJSONWrapper("Error","Rola vec postoji", null);
		
		return new RolaJSONWrapper("Success", "", rolaRepository.save(r));
	}

	@Override
	public RolaJSONWrapper getRola(Long id) {	
		
		Rola r = null;
		
		if((r = rolaRepository.findOne(id)) == null)
			return new RolaJSONWrapper("Error","Rola ne postoji", null);
		
		return new RolaJSONWrapper("Success", "", r);
	}

	@Override
	public RolaJSONWrapper updateRola(Long rolaId, Rola r) {
		
		Rola staraRola = null;
		
		if((staraRola = rolaRepository.findOne(rolaId)) == null)
			return new RolaJSONWrapper("Error","Rola ne postoji", null);
		
		staraRola.setNazivRole( r.getNazivRole() != null ? r.getNazivRole() : staraRola.getNazivRole());
		staraRola.setOpisRole(r.getOpisRole() != null ? r.getOpisRole() : staraRola.getOpisRole());
		staraRola.setAktivna(r.getAktivna() != null ? r.getAktivna() : staraRola.getAktivna());
		
		return new RolaJSONWrapper("Success", "", rolaRepository.save(staraRola));
		
	}

	@Override
	public Boolean deleteRola(Long rolaId) {
		
		Rola r = null;
		
		if((r = rolaRepository.findOne(rolaId)) == null)
			return false;
		
		r.setAktivna(false);
		
		rolaRepository.save(r);
		
		return true;
	}

	@Override
	public List<Rola> getRole() {
		
		return rolaRepository.findAll();
		
	}

}
