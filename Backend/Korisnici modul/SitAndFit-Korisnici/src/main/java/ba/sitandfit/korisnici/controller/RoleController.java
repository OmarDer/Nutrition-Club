package ba.sitandfit.korisnici.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ba.sitandfit.korisnici.jsonwrappers.RolaJSONWrapper;
import ba.sitandfit.korisnici.model.Rola;
import ba.sitandfit.korisnici.service.RolaService;

@RestController
@RequestMapping(value = "/role")
public class RoleController {
	
	@Autowired
	RolaService rolaService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Rola> getRole(){
		
		return rolaService.getRole();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public RolaJSONWrapper getStanje(@PathVariable(value="id") Long id){
		
		return rolaService.getRola(id);
		
	}
	
	@RequestMapping(value = "", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public RolaJSONWrapper createRola(@RequestBody Rola r){
		
		return rolaService.createRola(r);
		
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public RolaJSONWrapper updateRola(@PathVariable(value="id") Long id, @RequestBody Rola r){
		
		return rolaService.updateRola(id, r);
		
	}
	
	@RequestMapping(value = "/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public RolaJSONWrapper deleteRola(@PathVariable(value="id") Long id){
		
		if(rolaService.deleteRola(id))
			return new RolaJSONWrapper("Success", "Rola obrisana", null);
		else
			return new RolaJSONWrapper("Error", "Rola nije obrisana, desila se greška", null);
				
	}
	
	

}
