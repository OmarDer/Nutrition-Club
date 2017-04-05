package com.fitandsit.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fitandsit.Model.komentari;

public interface KomentariRepo extends PagingAndSortingRepository<komentari, Long> {
	List<komentari> findAll();
}
