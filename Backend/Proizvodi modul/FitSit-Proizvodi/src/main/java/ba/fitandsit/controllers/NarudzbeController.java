package ba.fitandsit.controllers;
import ba.fitandsit.wrappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ba.fitandsit.repository.NarudzbeRepository;
import ba.fitandsit.services.NarudzbeService;
import ba.fitandsit.services.ProizvodiService;
import ba.fitandsit.services.UsersCommunicationService;
import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Programi;

import java.util.List;

@RestController
@RequestMapping("/narudzbe")
public class NarudzbeController {
	
	@Autowired
	private NarudzbeService ns;
	
	@Autowired
	private ProizvodiService ps;
	
	@Autowired
	private UsersCommunicationService ucm;
	
	
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
	public JsonWrapperNarudzbe kreirajNarudzbu(@RequestBody Narudzbe p, @RequestHeader("Authorization") String token)
	{
		return ns.kreirajNarudzbu(p, token);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public Boolean obrisiNarudzbu(@PathVariable String id)
	{
		long Id=Long.parseLong(id);
		return ns.obrisiNarudzbu(Id);
	}
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public JsonWrapperNarudzbe azurirajNarudzbu(@PathVariable Long id,@RequestBody Narudzbe p, @RequestHeader("Authorization") String token){
		
		return ns.azurirajNarudzbu(id,p, token);
	}
	
	
	@RequestMapping(value="/kupac/{id}",method=RequestMethod.GET)
	public JsonWrapperListNarudzbe izlistajNarudzbu(@PathVariable String id, @RequestHeader("Authorization") String token)
	{
		Long Id=Long.parseLong(id);
		return ns.izlistajNarudzbuZaKupca(Id,token);
	}

	
	@RequestMapping(value="/prodavac/{id}",method=RequestMethod.GET)
	public JsonWrapperListNarudzbe izlistajNarudzbuZaProdavaca(@PathVariable Long id, @RequestHeader("Authorization") String token)
	{
		return ns.izlistajNarudzbuZaProdavaca(id, token);
	}
	
	
	@RequestMapping(value="/{id}/proizvod/{idp}",method=RequestMethod.POST)
	public JsonWrapperNarudzbe dodajProizvodeUNarudzbu(@PathVariable Long id, @PathVariable Long idp)
	{
		//Long Id=Long.parseLong(id);
		return ns.dodajProizvodeUNarudzbu(id,idp);
		
	}
	
	
	@RequestMapping(value="/{id}/proizvod/{idp}",method=RequestMethod.DELETE)
	public JsonWrapperNarudzbe obrisiProizvodIzNarudzbe(@PathVariable Long id, @PathVariable Long idp)//@RequestBody Identifikator p)
	{
		//Long Id=Long.parseLong(id);
		return ns.obrisiProizvodIzNarudzbe(id,idp);
	}
	
	
	@RequestMapping(value="/{id}/kupac",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public String vratiKupcaZaNarudzbu(@PathVariable Long id,@RequestHeader("Authorization") String token)
	{
		return ucm.vratiKupceZaNarudzbu(id,token);
	}
	
	
	@RequestMapping(value="/{id}/prodavac",method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String vratiProdavacaZaNarudzbu(@PathVariable Long id,@RequestHeader("Authorization") String token)
	{
		return ucm.vratiProdavacaZaNarudzbu(id,token);
	}
	
	@RequestMapping(value="/{nid}/proizvodi",method=RequestMethod.GET)
	public JsonWrapperListProizvodi vratiProizvodeZaNarudzbu(@PathVariable Long nid)
	{
		return ns.vratiProizvodeZaNarudzbu(nid);
	}
}
