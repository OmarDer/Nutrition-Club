package ba.fitandsit.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ba.fitandsit.repository.ProizvodiRepository;
import ba.fitandsit.services.ProizvodiService;
import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Proizvodi;

@RestController
@RequestMapping("/proizvodi")
public class ProizvodiController {
	
	@Autowired
	private ProizvodiService ps;
	
	
	@RequestMapping("")
	public List<Proizvodi> vratiSve(){	
		
		return ps.vratiSve();
		
	}
	
	@RequestMapping("/{id}")
	public Proizvodi vratiProizvodPoID(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		return ps.vratiProizvodPoID(Id);
		
	}
	
	@RequestMapping(value="/kreiraj",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public Proizvodi kreirajProizvod(@RequestBody Proizvodi p)
	{
		return ps.kreirajProizvod(p);
	}
	
	@RequestMapping(value="/obrisi/{id}", method=RequestMethod.DELETE)
	public Boolean obrisiProizvod(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		Proizvodi p=ps.vratiProizvodPoID(Id);
		return ps.obrisiProizvod(p);
	}
	
	@RequestMapping(value="/azuriraj",method=RequestMethod.PUT)
	public Proizvodi azurirajProizvod(@RequestBody Proizvodi p){
		
		return ps.azurirajProizvod(p);
	}
	@RequestMapping(value="/izlistaj/{programName}",method=RequestMethod.GET)
	public List<Proizvodi> izlistajProizvodePoProgramu(@PathVariable String programName)
	{
		return ps.izlistajProizvodePoProgramu(programName);
	}

}
