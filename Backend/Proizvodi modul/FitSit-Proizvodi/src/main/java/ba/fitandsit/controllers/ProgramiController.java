package ba.fitandsit.controllers;
import ba.fitandsit.wrappers.*;
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
	public JsonWrapperProgrami vratiProgramPoID(@PathVariable String id)
	{
		Long Id=Long.parseLong(id);
		return ps.vratiProgramPoID(Id);
		
	}
	
	@RequestMapping(value="",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public JsonWrapperProgrami kreirajProgram(@RequestBody Programi p)
	{
		return ps.kreirajProgram(p);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Boolean obrisiProgram(@PathVariable String id)
	{
		Long Id=Long.parseLong(id);
		return ps.obrisiProgram(Id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public JsonWrapperProgrami azurirajProgram(@PathVariable Long id,@RequestBody Programi p){
		
		return ps.azurirajProgram(id,p);
	}
	@RequestMapping(value="/izlistaj/{name}",method=RequestMethod.GET)
	public JsonWrapperProgrami izlistajProgramPoImenu(@PathVariable String name)
	{
		return ps.izlistajProgramPoImenu(name);
	}
	
	@RequestMapping(value="/dodaj/{id}",method=RequestMethod.POST)
	public JsonWrapperProgrami dodajProizvodeUProgram(@PathVariable String id, @RequestBody Identifikator p)
	{
		Long Id=Long.parseLong(id);
		return ps.dodajProizvodeUProgram(Id,p.getId());
		
	}
	@RequestMapping(value="/obrisi/{id}",method=RequestMethod.POST)
	public JsonWrapperProgrami obrisiProizvodeIzPrograma(@PathVariable String id, @RequestBody Identifikator p)
	{
		Long Id=Long.parseLong(id);
		return ps.obrisiProizvodeIzPrograma(Id,p.getId());
		
	}
}
