package ba.fitandsit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import ba.fitandsit.repository.NarudzbeRepository;
import ba.fitandsit.model.Narudzbe;
import java.util.List;

@RestController
@RequestMapping("/narudzbe")
public class NarudzbeController {
	
	@Autowired
	private NarudzbeRepository nr;
	
	@RequestMapping("/")
	public List<Narudzbe> dajSve()
	{
		return nr.findAll();
	}
	
	

}
