package ba.fitandsit.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ba.fitandsit.repository.ProizvodiRepository;
import ba.fitandsit.model.Proizvodi;

@RestController
@RequestMapping("/proizvodi")
public class ProizvodiController {
	
	@Autowired
	private ProizvodiRepository pr;
	
	@RequestMapping("/")
	public List<Proizvodi> dajSveProizvode(){
		return pr.findAll();
	}
	

}
