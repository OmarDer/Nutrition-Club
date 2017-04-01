package ba.sitandfit.korisnici.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.sitandfit.korisnici.jsonwrappers.PretplatnikJSONWrapper;
import ba.sitandfit.korisnici.model.Pretplatnik;
import ba.sitandfit.korisnici.repository.PretplatnikRepository;

@Service
public class PretplatnikServiceImpl implements PretplatnikService {
	
	PretplatnikRepository pretplatnikRepository;
	
	@Autowired
	public void setPretplatnikRepository(PretplatnikRepository pretplatnikRepository) {
		this.pretplatnikRepository = pretplatnikRepository;
	}

	@Override
	public PretplatnikJSONWrapper createPretplatnik(Pretplatnik p) {
		
		if (p.getId() != null && pretplatnikRepository.findOne(p.getId()) != null)
			return new PretplatnikJSONWrapper("Error", "Pretplatnik već postoji!", p);
		
		return new PretplatnikJSONWrapper("Success", "", pretplatnikRepository.save(p));
	}

	@Override
	public PretplatnikJSONWrapper getPretplatnik(Long id) {
		
		Pretplatnik x = pretplatnikRepository.findOne(id);
		 
		 if( x != null)
			 return new PretplatnikJSONWrapper("Success","", x);
		 else
			 return new PretplatnikJSONWrapper("Error","Trazeni pretplatnik ne postoji", null);
		 
	}

	@Override
	public PretplatnikJSONWrapper updatePretplatnik(Long id, Pretplatnik p) {
		
		Pretplatnik x;
		
		if((x = pretplatnikRepository.findOne(id)) == null)
			return new PretplatnikJSONWrapper("Error","Pretplatnik ne postoji!", null);
		
		x.setAktivan(p.getAktivan() != null ? p.getAktivan() : x.getAktivan());
		x.setEmail(p.getEmail() != null ? p.getEmail() : x.getEmail());
		
		return new PretplatnikJSONWrapper("Success","", pretplatnikRepository.save(x));
		
	}

	@Override
	public Boolean deletePretplatnik(Long id) {
		
		Pretplatnik x = null;
		
		if((x = pretplatnikRepository.findOne(id) ) == null)
			return false;
		
		x.setAktivan(false);
		
		pretplatnikRepository.save(x);
		
		return true;
	}

	@Override
	public List<Pretplatnik> getPretplatnici() {
		
		return pretplatnikRepository.findAll();
	}
	
	

}
