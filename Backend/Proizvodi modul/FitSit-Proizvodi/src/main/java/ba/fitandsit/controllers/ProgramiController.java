package ba.fitandsit.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ba.fitandsit.repository.ProgramiRepository;
import ba.fitandsit.model.Programi;

/*@RestController
@RequestMapping("/programi")
public class ProgramiController {
	
	private ProgramiRepository pr;
	@RequestMapping("/sve")
	public List<Programi> vratiSve(){	
		
		return pr.findAll();
		
	}

}*/
