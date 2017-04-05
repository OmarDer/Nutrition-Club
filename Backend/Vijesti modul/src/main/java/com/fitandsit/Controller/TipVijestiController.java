package com.fitandsit.Controller;

import jsonwrappers.tipvijestiJSONWrapper;
import services.TipVijestiService;

import com.fitandsit.Model.tipvijesti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipvijesti")
public class TipVijestiController {
	@Autowired
	TipVijestiService tvService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<tipvijesti> getAll(){
		
		return tvService.gettipvijestii();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public tipvijestiJSONWrapper getTipVijesti(@PathVariable(value="id") Long id){
		
		return tvService.gettipvijesti(id);
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public tipvijestiJSONWrapper createTipVijesti(@RequestBody tipvijesti tv){
		
		return tvService.createtipvijesti(tv);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public tipvijestiJSONWrapper updatetipVjesti(@PathVariable(value="id") Long id, @RequestBody tipvijesti tv){
		
		return tvService.updatetipvijesti(id, tv);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public tipvijestiJSONWrapper deleteTipVijesti(@PathVariable(value="id") Long id){
		
		if(tvService.deletetipvijesti(id))
			return new tipvijestiJSONWrapper("Success", "Tip vijesti obrisan", null);
		else
			return new tipvijestiJSONWrapper("Error", "Tip vijesti nije obrisan, desila se greska", null);
		
	}
}
