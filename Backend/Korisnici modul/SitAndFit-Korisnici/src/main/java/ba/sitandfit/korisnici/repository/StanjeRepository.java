package ba.sitandfit.korisnici.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ba.sitandfit.korisnici.model.Stanje;

@Repository
public interface StanjeRepository extends JpaRepository<Stanje, Long> {

	public List<Stanje> findAllByKorisnikId(Long korisnikId);
	
}
