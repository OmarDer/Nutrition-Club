package ba.sitandfit.korisnici.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ba.sitandfit.korisnici.model.Korisnik;

@RepositoryRestResource(collectionResourceRel = "korisnici", path = "korisnici")
public interface KorisnikRepository extends JpaRepository<Korisnik, Long>{
	
	Korisnik findByUserName(@Param("userName") String userName);
}
