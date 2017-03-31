package ba.sitandfit.korisnici.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import ba.sitandfit.korisnici.jsonwrappers.StanjeJSONWrapper;
import ba.sitandfit.korisnici.model.Stanje;
import ba.sitandfit.korisnici.service.StanjeService;

@RestController
@RequestMapping(value = "/stanja")
public class StanjaController {
	
	@Autowired
	StanjeService stanjeService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Stanje> getStanja(){
		
		return stanjeService.getStanja();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public StanjeJSONWrapper getStanje(@PathVariable(value="id") Long id){
		
		return stanjeService.getStanje(id);
		
	}
	
	@RequestMapping(value = "", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public StanjeJSONWrapper createStanje(@RequestBody Stanje s){
		
		return stanjeService.createStanje(s);
		
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public StanjeJSONWrapper updateStanje(@PathVariable(value="id") Long id, @RequestBody Stanje s){
		
		return stanjeService.updateStanje(id, s);
		
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public StanjeJSONWrapper deleteStanje(@PathVariable(value="id") Long id){
		
		if(stanjeService.deleteStanje(id))
			return new StanjeJSONWrapper("Success", "Stanje obrisano", null);
		else
			return new StanjeJSONWrapper("Error", "Stanje nije obrisano, desila se greška", null);
				
	}
	
	

}
