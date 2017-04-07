package com.fitandsit.Controller;

import jsonwrappers.KategorijeVijestiJSONWrapper;
import services.KategorijeVijestiService;

import com.fitandsit.Model.kategorijavijesti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kategorijeVijesti")
public class KategorijeVijestiController {
	
	@Autowired
	KategorijeVijestiService kvService;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<kategorijavijesti> getAll(){
		
		return kvService.getKategorijeVijesti();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KategorijeVijestiJSONWrapper getKategorijeVijesti(@PathVariable(value="id") Long id){
		
		return kvService.getkategorijevijesti(id);
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KategorijeVijestiJSONWrapper createKategorijeVijesti(@RequestBody kategorijavijesti k){
		
		return kvService.createkategorijevijesti(k);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KategorijeVijestiJSONWrapper updateKategorijeVjesti(@PathVariable(value="id") Long id, @RequestBody kategorijavijesti k){
		
		return kvService.updatekategorijevijesti(id, k);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KategorijeVijestiJSONWrapper deleteKategorijeVijesti(@PathVariable(value="id") Long id){
		
		if(kvService.deletevijesti(id))
			return new KategorijeVijestiJSONWrapper("Success", "Kategorija obrisana", null);
		else
			return new KategorijeVijestiJSONWrapper("Error", "Kategorija nije obrisan, desila se greska", null);
		
	}

	
}