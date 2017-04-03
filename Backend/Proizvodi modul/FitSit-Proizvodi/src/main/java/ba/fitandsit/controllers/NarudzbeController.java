package ba.fitandsit.controllers;
import ba.fitandsit.wrappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ba.fitandsit.repository.NarudzbeRepository;
import ba.fitandsit.services.NarudzbeService;
import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Programi;

import java.util.List;

@RestController
@RequestMapping("/narudzbe")
public class NarudzbeController {
	
	@Autowired
	private NarudzbeService ns;
	
	
	@RequestMapping("/all")
	public List<Narudzbe> vratiSve(){	
		
		return ns.vratiSve();
		
	}
	
	@RequestMapping("")
	public List<Narudzbe> vratiAktivne(){	
		
		return ns.vratiSveAktivne();
		
	}
	@RequestMapping("/{id}")
	public JsonWrapperNarudzbe vratiNarudzbuPoID(@PathVariable String id)
	{
		Long Id=Long.parseLong(id);	
		return ns.vratiNarudzbuPoID(Id);
		
	}
	
	@RequestMapping(value="",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public JsonWrapperNarudzbe kreirajNarudzbu(@RequestBody Narudzbe p)
	{
		return ns.kreirajNarudzbu(p);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Boolean obrisiNarudzbu(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		return ns.obrisiNarudzbu(Id);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public JsonWrapperNarudzbe azurirajNarudzbu(@PathVariable Long id,@RequestBody Narudzbe p){
		
		return ns.azurirajNarudzbu(id,p);
	}
	@RequestMapping(value="/izlistaj/{id}",method=RequestMethod.GET)
	public List<Narudzbe> izlistajNarudzbu(@PathVariable String id)
	{
		Long Id=Long.parseLong(id);
		return ns.izlistajNarudzbuZaKupca(Id);
	}

	@RequestMapping(value="/dodaj/{id}",method=RequestMethod.POST)
	public JsonWrapperNarudzbe dodajProizvodeUNarudzbu(@PathVariable String id, @RequestBody Identifikator p)
	{
		Long Id=Long.parseLong(id);
		return ns.dodajProizvodeUNarudzbu(Id,p.getId());
		
	}
	@RequestMapping(value="/obrisi/{id}",method=RequestMethod.POST)
	public JsonWrapperNarudzbe obrisiProizvodIzNarudzbe(@PathVariable String id, @RequestBody Identifikator p)
	{
		Long Id=Long.parseLong(id);
		return ns.obrisiProizvodIzNarudzbe(Id,p.getId());
	}
}
