package ba.sitandfit.korisnici.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ba.sitandfit.korisnici.model.Pretplatnik;

@RepositoryRestResource(collectionResourceRel = "pretplatnici", path = "pretplatnici")
public interface PretplatnikRepository extends JpaRepository<Pretplatnik, Long>{
	

}
