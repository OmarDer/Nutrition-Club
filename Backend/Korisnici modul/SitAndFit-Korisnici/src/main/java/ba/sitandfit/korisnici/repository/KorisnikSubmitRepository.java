package ba.sitandfit.korisnici.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import ba.sitandfit.korisnici.model.KorisnikSubmit;


public interface KorisnikSubmitRepository extends JpaRepository<KorisnikSubmit, Long> {

	List<KorisnikSubmit> findAll();
	
	@Query("select ks from KorisnikSubmit ks where ks.korisnikId = :id")
	KorisnikSubmit findByUserId(@Param("id") Long id);
	
	@Query("select ks from KorisnikSubmit ks where ks.generatedString = :gen")
	KorisnikSubmit findByGenString(@Param("gen") String gen);
	
}
