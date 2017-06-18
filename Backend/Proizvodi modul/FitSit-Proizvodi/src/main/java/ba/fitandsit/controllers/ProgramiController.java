package ba.fitandsit.controllers;
import ba.fitandsit.wrappers.*;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.inject.spi.Message;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import ba.fitandsit.repository.ProgramiRepository;
import ba.fitandsit.services.ProgramiService;
import ba.fitandsit.services.UsersCommunicationService;
import ba.fitandsit.model.Narudzbe;
import ba.fitandsit.model.Programi;
import ba.fitandsit.model.Proizvodi;
import ba.fitandsit.servicediscovery.*;

@RestController
@RequestMapping("/programi")
public class ProgramiController {
	
	@Autowired
	private ProgramiService ps;
	
	@Autowired
	private UsersCommunicationService ucm;
	
	@Autowired
	private ServiceUrl su;
	
	@RequestMapping(value="", produces="application/json")
	public List<Programi> vratiAktivneBezKorisnika(){	
		
		return ps.vratiAktivne();
		
	}
	
	@RequestMapping("/all")
	public List<Programi> vratiSve(){	
		
		return ps.vratiSve();
		
	}
	
	@RequestMapping("/korisnici")
	public List<Programi> vratiBezKorisnika(){	
		
		return ps.vratiSaKorisnikom();
		
	}
	//Vratiti sve programe koji imaju korisnike
	@RequestMapping(value="/korisnici/all", produces="application/json")
	public List<Programi> vratiAktivne(){	
		
		return ps.vratiSaKorisnikomSve();
		
	}
	
	//Vratiti samo programe koji imaju korisnike
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
	
	@RequestMapping(value="/{id}/proizvod/{idp}",method=RequestMethod.POST)
	public JsonWrapperProgrami dodajProizvodeUProgram(@PathVariable Long id, @PathVariable Long idp)// @RequestBody Identifikator p)
	{
		return ps.dodajProizvodeUProgram(id,idp);	
	}
	@RequestMapping(value="/{id}/proizvod/{idp}",method=RequestMethod.DELETE)
	public JsonWrapperProgrami obrisiProizvodeIzPrograma(@PathVariable Long id, @PathVariable Long idp)//, @RequestBody Identifikator p)
	{
		return ps.obrisiProizvodeIzPrograma(id,idp);	
	}
	
	@RequestMapping(value="/korisnik/{id}")
	public JsonWrapperProgrami vratiProgramZaKorisnika(@PathVariable Long id, @RequestHeader("Authorization") String token)
	{
		return ps.vratiProgramZaKorisnika(id,token);
	}
	
	@RequestMapping(value="/{id}/korisnik",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public String dajKorisnikaZaProgram(@PathVariable Long id, @RequestHeader("Authorization") String token ) 
	{
		return ucm.dajKorisnikaZaProgram(id,token);
	}
	
	/*
	private DiscoveryClient discoveryClient;
	
	@Autowired
	public void setDiscoveryClient(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}
	
	public String serviceUrl() {
		
	    List<ServiceInstance> list = discoveryClient.getInstances("KORISNICI");
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }
	    return null;
	}
	*/
}
