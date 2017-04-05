package com.fitandsit.Repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.fitandsit.Model.kategorijavijesti;


public interface KategorijeVijestiRepo extends PagingAndSortingRepository<kategorijavijesti, Long>{

	List<kategorijavijesti> findAll();

}