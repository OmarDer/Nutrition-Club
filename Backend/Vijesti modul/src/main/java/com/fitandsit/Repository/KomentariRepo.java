package com.fitandsit.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fitandsit.Model.komentari;

public interface KomentariRepo extends PagingAndSortingRepository<komentari, Long> {
	List<komentari> findAll();
	
	@Query("select k from komentari k where k.aktivan = 1 ")
	List<komentari> findAktivne();
	
	@Query("select k from komentari k where k.AutorID = :id ")
	List<komentari> findByAutorID(@Param("id")Long id);
}
