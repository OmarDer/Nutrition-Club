package ba.sitandfit.korisnici.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
