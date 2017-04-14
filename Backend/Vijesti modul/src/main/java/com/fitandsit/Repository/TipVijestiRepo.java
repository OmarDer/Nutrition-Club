package com.fitandsit.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fitandsit.Model.tipvijesti;

public interface TipVijestiRepo extends PagingAndSortingRepository<tipvijesti, Long> {
	List<tipvijesti> findAll();
	
	@Query("select tv from tipvijesti tv where tv.aktivan = 1 ")
	List<tipvijesti> findAktivne();
}
