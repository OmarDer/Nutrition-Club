package com.fitandsit.Controller;

import com.fitandsit.Model.komentari;
import com.fitandsit.jsonwrappers.KomentariJSONWrapper;
import com.fitandsit.jsonwrappers.KomentariListJSONWrapper;
import com.fitandsit.jsonwrappers.VijestiJSONWrapper;
import com.fitandsit.services.KorisniciCommunication;
import com.fitandsit.services.komentariService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/komentari")
public class KomentariController {
	@Autowired
	komentariService kService;
	
	@Autowired
	KorisniciCommunication kc;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<komentari> getAll(){
		
		return kService.getKomentari();
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<komentari> getAllAktiv(){
		
		return kService.getAktivKomentari();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KomentariJSONWrapper getKomentar(@PathVariable(value="id") Long id){
		
		return kService.getKomentar(id);
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KomentariJSONWrapper createKomentar(@RequestBody komentari k){
		
		return kService.createKomentar(k);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KomentariJSONWrapper updateKomentar(@PathVariable(value="id") Long id, @RequestBody komentari k){
		
		return kService.updateKomentar(id, k);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public KomentariJSONWrapper deleteKomentar(@PathVariable(value="id") Long id){
		
		if(kService.deleteKomentar(id))
			return new KomentariJSONWrapper("Success", "Komentar obrisan", null);
		else
			return new KomentariJSONWrapper("Error", "Komentar nije obrisan, desila se greska", null);
		
	}
	
	@RequestMapping(value = "/{id}/vijest", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public VijestiJSONWrapper getVijestKomentara(@PathVariable(value="id") Long id){
		
		return kService.getvijesti(id);
		
	}
	
	@RequestMapping(value = "/{id}/komentar", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KomentariJSONWrapper getKomentarKomentara(@PathVariable(value="id") Long id){
		
		return kService.getchildkomentar(id);
		
	}
	@RequestMapping(value = "/{id}/autorInfo/", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getAutorKomentar(@PathVariable(value="id") Long id){
		
		return kc.getAutorKomentar(id);
		
	}
	
	@RequestMapping(value = "/{id}/autor/", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KomentariListJSONWrapper getKomentarAutor(@PathVariable(value="id") Long id){
		
		return kService.getKomentarKorisnik(id);
		
	}
}
