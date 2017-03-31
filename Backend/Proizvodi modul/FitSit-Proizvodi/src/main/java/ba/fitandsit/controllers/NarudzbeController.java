package ba.fitandsit.controllers;

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
	
	
	@RequestMapping("")
	public List<Narudzbe> vratiSve(){	
		
		return ns.vratiSve();
		
	}
	
	@RequestMapping("/{id}")
	public Narudzbe vratiNarudzbuPoID(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		return ns.vratiNarudzbuPoID(Id);
		
	}
	
	@RequestMapping(value="/kreiraj",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public Narudzbe kreirajNarudzbu(@RequestBody Narudzbe p)
	{
		return ns.kreirajNarudzbu(p);
	}
	
	@RequestMapping(value="/obrisi/{id}", method=RequestMethod.DELETE)
	public Boolean obrisiNarudzbu(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		Narudzbe n=ns.vratiNarudzbuPoID(Id);
		return ns.obrisiNarudzbu(n);
	}
	
	@RequestMapping(value="/azuriraj",method=RequestMethod.PUT)
	public Narudzbe azurirajNarudzbu(@RequestBody Narudzbe p){
		
		return ns.azurirajNarudzbu(p);
	}
	@RequestMapping(value="/izlistaj/{id}",method=RequestMethod.GET)
	public List<Narudzbe> izlistajNarudzbu(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		return ns.izlistajNarudzbuZaKupca(Id);
	}

}
