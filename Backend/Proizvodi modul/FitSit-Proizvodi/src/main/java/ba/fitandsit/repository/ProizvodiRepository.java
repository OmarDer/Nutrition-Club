package ba.fitandsit.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Programi;
import ba.fitandsit.model.Proizvodi;

public interface ProizvodiRepository extends JpaRepository<Proizvodi,Long> {
	
	@Query("select pr.proizvodiList from Programi pr "
			+ "where pr.naziv_programa = :name ")
	List<Proizvodi> findByProgramName(@Param("name") String name);
	
	@Query("select p from Proizvodi p where p.aktivan = :1 ")
	List<Proizvodi> findAktivne();

}
