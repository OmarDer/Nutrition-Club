package ba.fitandsit.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ba.fitandsit.model.Programi;

public interface ProgramiRepository extends PagingAndSortingRepository<Programi, Long>{
	 
	List<Programi> findAll();
}
