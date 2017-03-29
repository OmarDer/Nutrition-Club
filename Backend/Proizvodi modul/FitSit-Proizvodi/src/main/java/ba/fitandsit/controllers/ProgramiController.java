package ba.fitandsit.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ba.fitandsit.repository.ProgramiRepository;
import ba.fitandsit.services.ProgramiService;
import ba.fitandsit.model.Programi;
import ba.fitandsit.model.Proizvodi;

@RestController
@RequestMapping("/programi")
public class ProgramiController {
	
	@Autowired
	private ProgramiService ps;
	
	@RequestMapping("")
	public List<Programi> vratiSve(){	
		
		return ps.vratiSve();
		
	}
	
	@RequestMapping("/{id}")
	public Programi vratiProgramPoID(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		return ps.vratiProgramPoID(Id);
		
	}
	
	@RequestMapping(value="/kreiraj",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public Programi kreirajProgram(@RequestBody Programi p)
	{
		return ps.kreirajProgram(p);
	}
	
	@RequestMapping(value="/obrisi/{id}", method=RequestMethod.DELETE)
	public Boolean obrisiProgram(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		Programi p=ps.vratiProgramPoID(Id);
		return ps.obrisiProgram(p);
	}
	
	@RequestMapping(value="/azuriraj",method=RequestMethod.PUT)
	public Programi azurirajProgram(@RequestBody Programi p){
		
		return ps.azurirajProgram(p);
	}
	@RequestMapping(value="/izlistaj/{name}",method=RequestMethod.GET)
	public Programi izlistajProgramPoImenu(@PathVariable String name)
	{
		return ps.izlistajProgramPoImenu(name);
	}
	
	@RequestMapping(value="/dodaj/{id}",method=RequestMethod.POST)
	public Programi dodajProizvodeUProgram(@PathVariable String id, @RequestBody Proizvodi p)
	{
		long Id=Long.parseLong(id);
		return ps.dodajProizvodeUProgram(Id,p);
		
	}

}
