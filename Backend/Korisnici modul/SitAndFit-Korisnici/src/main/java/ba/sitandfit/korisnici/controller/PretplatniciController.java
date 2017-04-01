package ba.sitandfit.korisnici.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ba.sitandfit.korisnici.jsonwrappers.PretplatnikJSONWrapper;
import ba.sitandfit.korisnici.model.Pretplatnik;
import ba.sitandfit.korisnici.service.PretplatnikService;

@RestController
@RequestMapping(value="/pretplatnici")
public class PretplatniciController {
	
	@Autowired
	PretplatnikService pretplatnikService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Pretplatnik> getPretplatnici(){
		
		return pretplatnikService.getPretplatnici();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public PretplatnikJSONWrapper getPretplatnik(@PathVariable(value="id") Long id){
		
		return pretplatnikService.getPretplatnik(id);
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public PretplatnikJSONWrapper createPretplatnik(@RequestBody Pretplatnik p){
		
		return pretplatnikService.createPretplatnik(p);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public PretplatnikJSONWrapper updatePretplatnik(@PathVariable(value="id") Long id, @RequestBody Pretplatnik p){
		
		return pretplatnikService.updatePretplatnik(id, p);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public PretplatnikJSONWrapper deletePretplatnik(@PathVariable(value="id") Long id){
		
		if(pretplatnikService.deletePretplatnik(id))
			return new PretplatnikJSONWrapper("Success", "Pretplatnik obrisan", null);
		else
			return new PretplatnikJSONWrapper("Error", "Pretplatnik nije obrisan, desila se greska", null);
		
		
		
	}

}
