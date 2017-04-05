package com.fitandsit.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fitandsit.Model.tipvijesti;

public interface TipVijestiRepo extends PagingAndSortingRepository<tipvijesti, Long> {
	List<tipvijesti> findAll();
}
