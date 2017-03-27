package ba.sitandfit.korisnici.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Rola createRola(Rola r) {
		
		if(rolaRepository.findOne(r.getId()) != null)
			return null;
		
		return rolaRepository.save(r);
	}

	@Override
	public Rola getRola(Long id) {	
		return rolaRepository.findOne(id);
	}

	@Override
	public Rola updateRola(Rola r) {
		
		if(rolaRepository.findOne(r.getId()) == null)
			return null;
		
		rolaRepository.delete(r.getId());
		
		return rolaRepository.save(r);
		
	}

	@Override
	public Boolean deleteRola(Rola r) {
		
		if(rolaRepository.findOne(r.getId()) == null)
			return false;
		
		rolaRepository.delete(r.getId());
		
		return true;
	}

	@Override
	public List<Rola> getRole() {
		
		return rolaRepository.findAll();
	}

}
