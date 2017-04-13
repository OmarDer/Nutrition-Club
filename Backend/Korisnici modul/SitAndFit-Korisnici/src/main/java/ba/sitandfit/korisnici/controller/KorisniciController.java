package ba.sitandfit.korisnici.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.jsonwrappers.RolaJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.model.Stanje;
import ba.sitandfit.korisnici.service.KorisnikService;
import ba.sitandfit.korisnici.service.RolaService;
import ba.sitandfit.korisnici.service.StanjeService;

@RestController
@RequestMapping(value = "/korisnici")
public class KorisniciController {
	
	@Autowired
	KorisnikService korisnikService;
	@Autowired
	StanjeService stanjeService;
	@Autowired
	RolaService rolaService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Korisnik> getKorisnici(){
		
		return korisnikService.getKorisnici();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper getKorisnik(@PathVariable(value="id") Long id){
		
		return korisnikService.getKorisnik(id);
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper createKorisnik(@RequestBody Korisnik k){
		
		return korisnikService.createKorisnik(k);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper updateKorisnik(@PathVariable(value="id") Long id, @RequestBody Korisnik k){
		
		return korisnikService.updateKorisnik(id, k);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper deleteKorisnik(@PathVariable(value="id") Long id){
		
		if(korisnikService.deleteKorisnik(id))
			return new KorisnikJSONWrapper("Success", "Korisnik obrisan", null);
		else
			return new KorisnikJSONWrapper("Error", "Korisnik nije obrisan, desila se greska", null);
		
		
		
	}
	
	@RequestMapping(value = "/{id}/stanjakorisnika", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Stanje> getStanjaKorisnikaByDate(@PathVariable(value="id") Long id, @RequestParam(value = "datumAnalize", required=false) Date date, 
																					@RequestParam(value = "startDate", required=false) Date startDate, 
																					@RequestParam(value = "endDate", required=false) Date endDate){
		
		if (date != null){
			return stanjeService.getStanjaKorisnikaByDate(id, date);
		}
		else if (endDate != null && startDate != null)
			return stanjeService.getStanjaKorisnikaBetweenDates(id, startDate, endDate);
		else
			return stanjeService.getStanjaKorisnika(id);
		
	}
	
	@RequestMapping(value = "/{id}/rola", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public RolaJSONWrapper getRolaKorisnika(@PathVariable(value="id") Long id){
		
		return korisnikService.getRolaKorisnika(id);
		
	}
	
	@RequestMapping(value = "/{id}/programi", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getProgrameKorisnika(@PathVariable(value="id") Long id){
		
		return korisnikService.getProgrameKorisnika(id);
		
	}
	
	

}
