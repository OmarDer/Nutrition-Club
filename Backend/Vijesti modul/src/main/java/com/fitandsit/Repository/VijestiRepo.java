package com.fitandsit.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fitandsit.Model.vijesti;

public interface VijestiRepo extends PagingAndSortingRepository<vijesti,Long> {
	List<vijesti> findAll();
	
	@Query("select v from vijesti v where v.aktivan = 1 ")
	List<vijesti> findAktivne();
	
	@Query("select v from vijesti v where v.AutorID = :id ")
	List<vijesti> findByAutorID(@Param("id")Long id);
}
