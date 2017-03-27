package ba.sitandfit.korisnici.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.service.KorisnikService;

@RestController
@RequestMapping(value = "/korisnici")
public class KorisniciController {
	
	@Autowired
	KorisnikService korisnikService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Korisnik> getKorisnici(){
		
		return korisnikService.getKorisnici();
		
	}
	
	@RequestMapping(value = "/kreiraj", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper createKorisnik(@RequestBody Korisnik k){
		
		return korisnikService.createKorisnik(k);
		
	}
	
	@RequestMapping(value = "/azuriraj", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikJSONWrapper updateKorisnik(@RequestBody Korisnik k){
		
		return korisnikService.updateKorisnik(k);
		
	}
	
	@RequestMapping(value = "/izbrisi", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public String deleteKorisnik(@RequestBody Korisnik k){
		
		return "{ \"status\":\"" + (korisnikService.deleteKorisnik(k) ? "Success" : "Greska") + "\" }";
		
	}

}
