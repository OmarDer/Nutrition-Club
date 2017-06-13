package ba.sitandfit.korisnici.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ba.sitandfit.korisnici.model.Rola;

@RepositoryRestResource(collectionResourceRel = "role", path = "role")
public interface RolaRepository extends JpaRepository<Rola, Long>{
	
	Rola findByNazivRole(@Param("nazivRole") String nazivRole);

}
