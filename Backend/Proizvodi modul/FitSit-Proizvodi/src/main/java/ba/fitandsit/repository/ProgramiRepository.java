package ba.fitandsit.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Programi;

//@RepositoryRestResource(path="programs", collectionResourceRel="programs")
public interface ProgramiRepository extends JpaRepository<Programi, Long>{
	 
	List<Programi> findAll();
	@Query("select p from Programi p where p.naziv_programa = :name ")
	Programi findBynaziv_programa(@Param("name") String name);
	
	@Query("select p from Programi p where p.aktivan = 1 ")
	List<Programi> findAktivne();
}
