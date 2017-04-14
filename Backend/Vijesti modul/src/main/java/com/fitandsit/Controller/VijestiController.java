package com.fitandsit.Controller;

import jsonwrappers.KategorijeVijestiJSONWrapper;
import jsonwrappers.VijestiJSONWrapper;
import jsonwrappers.VijestiListJSONWrapper;
import jsonwrappers.tipvijestiJSONWrapper;
import services.KorisniciCommunication;
import services.VijestiService;

import com.fitandsit.Model.vijesti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vijesti")
public class VijestiController {
	@Autowired
	VijestiService vService;
	
	@Autowired
	KorisniciCommunication kc;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<vijesti> getAll(){
		
		return vService.getVijestii();
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<vijesti> getAllAktiv(){
		
		return vService.getAktivVijestii();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public VijestiJSONWrapper getVijesti(@PathVariable(value="id") Long id){
		
		return vService.getvijesti(id);
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public VijestiJSONWrapper createVijesti(@RequestBody vijesti v){
		
		return vService.createvijesti(v);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public VijestiJSONWrapper updateVjesti(@PathVariable(value="id") Long id, @RequestBody vijesti v){
		
		return vService.updatevijesti(id, v);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public VijestiJSONWrapper deleteVijesti(@PathVariable(value="id") Long id){
		
		if(vService.deletevijesti(id))
			return new VijestiJSONWrapper("Success", "Vijest obrisana", null);
		else
			return new VijestiJSONWrapper("Error", "Vijest nije obrisana, desila se greska", null);
	}
	
	@RequestMapping(value = "/{id}/tip", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public tipvijestiJSONWrapper getTipVijesti(@PathVariable(value="id") Long id){
		
		return vService.gettipvijesti(id);
		
	}
	
	@RequestMapping(value = "/{id}/kategorija", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public KategorijeVijestiJSONWrapper getKategorijaVijesti(@PathVariable(value="id") Long id){
		
		return vService.getkategorijavijesti(id);
		
	}
	
	@RequestMapping(value = "/{id}/autorInfo/", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public String getAutorVijest(@PathVariable(value="id") Long id){
		
		return kc.getAutorVijest(id);
		
	}
	
	@RequestMapping(value = "/{id}/autor/", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public VijestiListJSONWrapper getVijestAutor(@PathVariable(value="id") Long id){
		
		return vService.getVijestKorisnik(id);
		
	}
	
}
