package ba.fitandsit.controllers;
import ba.fitandsit.wrappers.*;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ba.fitandsit.repository.ProizvodiRepository;
import ba.fitandsit.services.ProizvodiService;
import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Programi;
import ba.fitandsit.model.Proizvodi;

@RestController
@RequestMapping("/proizvodi")
public class ProizvodiController {
	
	@Autowired
	private ProizvodiService ps;
	
	
	@RequestMapping("/all")
	public List<Proizvodi> vratiSve(){	
		
		return ps.vratiSve();
		
	}
	
	@RequestMapping("")
	public List<Proizvodi> vratiAktivne(){	
		
		return ps.vratiAktivne();
		
	}
	@RequestMapping("/{id}")
	public JsonWrapperProizvodi vratiProizvodPoID(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		return ps.vratiProizvodPoID(Id);
		
	}
	
	@RequestMapping(value="",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public JsonWrapperProizvodi kreirajProizvod(@RequestBody Proizvodi p, @RequestHeader("Authorization") String token)
	{
		return ps.kreirajProizvod(p,token);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Boolean obrisiProizvod(@PathVariable String id)
	{
		Long Id=Long.parseLong(id);
	
		return ps.obrisiProizvod(Id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public JsonWrapperProizvodi azurirajProizvod(@PathVariable Long id, @RequestBody Proizvodi p, @RequestHeader("Authorization") String token){
		
		return ps.azurirajProizvod(id,p,token);
	}
	@RequestMapping(value="/izlistaj/{programName}",method=RequestMethod.GET)
	public JsonWrapperListProizvodi izlistajProizvodePoProgramu(@PathVariable String programName)
	{
		return ps.izlistajProizvodePoProgramu(programName);
	}
	
}
