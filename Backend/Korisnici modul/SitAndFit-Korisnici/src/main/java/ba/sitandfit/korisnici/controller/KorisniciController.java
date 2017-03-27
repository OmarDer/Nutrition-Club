package ba.sitandfit.korisnici.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.service.KorisnikService;

@RestController
@RequestMapping(value = "/korisnici")
public class KorisniciController {
	
	@Autowired
	KorisnikService korisnikService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Korisnik> getKorisnici(){
		
		return korisnikService.getKorisnici();
		
	}
	

}
