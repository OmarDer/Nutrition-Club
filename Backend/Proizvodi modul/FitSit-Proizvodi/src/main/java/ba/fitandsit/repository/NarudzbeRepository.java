package ba.fitandsit.repository;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.repository.CrudRepository;

import ba.fitandsit.model.Narudzbe;

public interface NarudzbeRepository extends PagingAndSortingRepository<Narudzbe,Long> {

	List<Narudzbe> findAll();
}
