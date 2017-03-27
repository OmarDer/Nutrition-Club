package ba.sitandfit.korisnici.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.repository.KorisnikRepository;

@Service
public class KorisnikServiceImpl implements KorisnikService {
	

	KorisnikRepository korisnikRepository;
	
	@Autowired
	public void setKorisnikRepository(KorisnikRepository korisnikRepository) {
		this.korisnikRepository = korisnikRepository;
	}

	@Override
	public Korisnik createKorisnik(Korisnik k) {
		
		if (korisnikRepository.findOne(k.getId()) != null)
			return null;
		
		return korisnikRepository.save(k);
		
	}

	@Override
	public Korisnik getKorisnik(Long id) {
		
		return korisnikRepository.getOne(id);
	}

	@Override
	public Korisnik updateKorisnik(Korisnik k) {
		
		if(korisnikRepository.findOne(k.getId()) != null)
			return null;
		
		korisnikRepository.delete(k.getId());
		
		return korisnikRepository.save(k);
		
		
	}

	@Override
	public Boolean deleteKorisnik(Korisnik k) {
		
		if(korisnikRepository.findOne(k.getId()) != null)
			return false;
		
		korisnikRepository.delete(k.getId());
		
		return true;
		
	}

	@Override
	public List<Korisnik> getKorisnici() {
		
		return korisnikRepository.findAll();
	}

}
