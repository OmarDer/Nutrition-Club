package ba.sitandfit.korisnici.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import ba.sitandfit.korisnici.jsonwrappers.KorisnikJSONWrapper;
import ba.sitandfit.korisnici.jsonwrappers.KorisnikSubmitJSONWrapper;
import ba.sitandfit.korisnici.jsonwrappers.RolaJSONWrapper;
import ba.sitandfit.korisnici.model.Korisnik;
import ba.sitandfit.korisnici.model.KorisnikSubmit;
import ba.sitandfit.korisnici.model.Stanje;
import ba.sitandfit.korisnici.service.KorisnikService;
import ba.sitandfit.korisnici.service.KorisnikSubmitService;
import ba.sitandfit.korisnici.service.RolaService;
import ba.sitandfit.korisnici.service.StanjeService;

@RestController
@CrossOrigin
@RequestMapping(value = "/submit")
public class KorisnikSubmitController {
	
	@Autowired
	KorisnikSubmitService kss;
	
	@Autowired
	KorisnikService korService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikSubmitJSONWrapper getKorisnikSubmit(@PathVariable(value="id") Long id){
		
		return kss.getKorisnikSubmit(id);	
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<KorisnikSubmit> getKorisnikSubmits(){
		
		return kss.getKorisnikSubmits();	
		
	}
	
	@RequestMapping(value = "/korisnik/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikSubmitJSONWrapper getKorisnikSubmitByUserId(@PathVariable(value="id") Long id){
		
		return kss.getKorisnikSubmitByUserId(id);	
		
	}
	
	@RequestMapping(value = "/string/{gen}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikSubmitJSONWrapper getKorisnikSubmitByGenString(@PathVariable(value="gen") String gen){
		
		return kss.getKorisnikSubmitByGenString(gen);	
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikSubmitJSONWrapper createKorisnikSubmit(@RequestBody KorisnikSubmit k){
		
		return kss.createKorisnikSubmit(k);
		
	}
	
	@RequestMapping(value = "/potvrdi/{gen}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KorisnikSubmitJSONWrapper potvrdiKorisnikSubmit(@PathVariable(value="gen") String gen){
		
		KorisnikSubmitJSONWrapper ks = kss.getKorisnikSubmitByGenString(gen);
		korService.aktivirajKorisnika(ks.getKs().getKorisnikId());
		kss.potvrdiKorisnikSubmit(ks.getKs());
		return kss.potvrdiKorisnikSubmit(ks.getKs());
		
	}
	
	

}
