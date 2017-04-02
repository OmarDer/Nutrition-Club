package ba.fitandsit.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ba.fitandsit.model.Narudzbe;

public interface NarudzbeRepository extends JpaRepository<Narudzbe,Long> {

	List<Narudzbe> findAll();
	@Query("select n from Narudzbe n where n.korisnikID = :id ")
	List<Narudzbe> findBykorisnikID(@Param("id")long id);
	
	@Query("select n from Narudzbe n where n.prodavacID = :id ")
	List<Narudzbe> findByprodavacID(@Param("id")long id);
}