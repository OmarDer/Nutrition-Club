package ba.sitandfit.korisnici.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ba.sitandfit.korisnici.model.Stanje;

@RepositoryRestResource(collectionResourceRel = "stanjakorisnika", path = "stanjakorisnika")
public interface StanjeRepository extends JpaRepository<Stanje, Long> {

	List<Stanje> findAllByKorisnikId(@Param("id") Long korisnikId);
	
	List<Stanje> findByDatumAnalizeAndKorisnikId(@Param("datumAnalize") Date datumAnalize, @Param("id") Long korisnikId);
	
	@Query("SELECT s FROM Stanje s WHERE s.korisnik.id = :id AND s.datumAnalize BETWEEN :startDate AND :endDate")
	List<Stanje> findStanjaKorisnikaBetweenDates(@Param("id") Long id, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
}
