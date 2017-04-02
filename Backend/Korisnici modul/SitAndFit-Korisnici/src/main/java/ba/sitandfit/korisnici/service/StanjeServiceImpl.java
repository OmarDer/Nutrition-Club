package ba.sitandfit.korisnici.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ba.sitandfit.korisnici.jsonwrappers.StanjeJSONWrapper;
import ba.sitandfit.korisnici.model.Stanje;
import ba.sitandfit.korisnici.repository.StanjeRepository;

@Service
public class StanjeServiceImpl implements StanjeService {
	
	StanjeRepository stanjeRepository;

	@Autowired
	public void setStanjeRepository(StanjeRepository stanjeRepository) {
		this.stanjeRepository = stanjeRepository;
	}

	@Override
	public List<Stanje> getStanjaKorisnika(Long korisnikId) {
		
		return stanjeRepository.findAllByKorisnikId(korisnikId);
	}

	@Override
	public StanjeJSONWrapper createStanje(Stanje s) {
		
		if(s.getId() != null && stanjeRepository.findOne(s.getId()) != null)
			return new StanjeJSONWrapper("Error","Stanje vec postoji", null);
		
		return new StanjeJSONWrapper("Success", "", stanjeRepository.save(s));
	}

	@Override
	public StanjeJSONWrapper getStanje(Long id) {
		
		Stanje s = null;
		
		if((s = stanjeRepository.findOne(id)) == null)
			return new StanjeJSONWrapper("Error","Stanje ne postoji", null);
		
		return new StanjeJSONWrapper("Success", "", s);
	}

	@Override
	public StanjeJSONWrapper updateStanje(Long id, Stanje s) {
		
		Stanje staroStanje = null;
		
		if((staroStanje = stanjeRepository.findOne(id)) == null)
			return new StanjeJSONWrapper("Error","Stanje ne postoji", null);
		
		staroStanje.setBazalniMetabolizam(s.getBazalniMetabolizam() != null ? s.getBazalniMetabolizam() : staroStanje.getBazalniMetabolizam());
		staroStanje.setDatumAnalize(s.getDatumAnalize() != null ? s.getDatumAnalize() : staroStanje.getDatumAnalize());
		staroStanje.setFizickoStanje(s.getFizickoStanje() != null ? s.getFizickoStanje() : staroStanje.getFizickoStanje());
		staroStanje.setKostanaMasa(s.getKostanaMasa() != null ? s.getKostanaMasa() : staroStanje.getKostanaMasa());
		staroStanje.setMetabolickeGodine(s.getMetabolickeGodine() != null ? s.getMetabolickeGodine() : staroStanje.getMetabolickeGodine());
		staroStanje.setOrganskeMasnoce(s.getOrganskeMasnoce() != null ? s.getOrganskeMasnoce() : staroStanje.getOrganskeMasnoce());
		staroStanje.setPostotakMisica(s.getPostotakMisica() != null ? s.getPostotakMisica() : staroStanje.getPostotakMisica());
		staroStanje.setProcenatMasnoce(s.getProcenatMasnoce() != null ? s.getProcenatMasnoce() : staroStanje.getProcenatMasnoce());
		staroStanje.setProcenatVode(s.getProcenatVode() != null ? s.getProcenatVode() : staroStanje.getProcenatVode());
		staroStanje.setTezina(s.getTezina() != null ? s.getTezina() : staroStanje.getTezina());
		staroStanje.setAktivno(s.getAktivno() != null ? s.getAktivno() : staroStanje.getAktivno());
		
		return new StanjeJSONWrapper("Success","", stanjeRepository.save(staroStanje));
		
	}

	@Override
	public Boolean deleteStanje(Long id) {
		
		Stanje s = null;
		
		if((s = stanjeRepository.findOne(id)) == null)
			return false;
		
		s.setAktivno(false);
		
		stanjeRepository.save(s);
		
		return true;
	}

	@Override
	public List<Stanje> getStanja() {
		
		return stanjeRepository.findAll();
	}

	@Override
	public List<Stanje> getStanjaKorisnikaByDate(Long id, Date datum) {
		
		return stanjeRepository.findByDatumAnalizeAndKorisnikId((java.sql.Date) datum, id);
		
	}

	@Override
	public List<Stanje> getStanjaKorisnikaBetweenDates(Long id, Date startDate, Date endDate) {
		
		return stanjeRepository.findStanjaKorisnikaBetweenDates(id, (java.sql.Date)startDate, (java.sql.Date)endDate);
	}

}
