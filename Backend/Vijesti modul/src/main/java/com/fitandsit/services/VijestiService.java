package com.fitandsit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitandsit.Model.vijesti;
import com.fitandsit.Repository.VijestiRepo;
import com.fitandsit.jsonwrappers.KategorijeVijestiJSONWrapper;
import com.fitandsit.jsonwrappers.VijestiJSONWrapper;
import com.fitandsit.jsonwrappers.VijestiListJSONWrapper;
import com.fitandsit.jsonwrappers.tipvijestiJSONWrapper;

@Service
public class VijestiService implements VijestiInterface {
	
	VijestiRepo vijestirepo;
	
	@Autowired
	public KorisniciCommunication kc;
	
	@Autowired
	public void setVijestiRepo(VijestiRepo vijestirepo) {
		this.vijestirepo = vijestirepo;
	}
	
	@Override
	public VijestiJSONWrapper createvijesti(vijesti v){
		if (v.getVijestID()!=null && vijestirepo.findOne(v.getVijestID()) != null)
			return new VijestiJSONWrapper("Error", "Vijesti već postoji!", v);
		
		if(v.getAutorID()!=null && kc.checkKorisnik(v.getAutorID())==false)
		{
			return new VijestiJSONWrapper("Error","Uneseni korisnik ne postoji!",null);
		}
		
		return new VijestiJSONWrapper("Success", "", vijestirepo.save(v));
	}
	
	@Override
	public VijestiJSONWrapper getvijesti(Long id){
		vijesti tv =vijestirepo.findOne(id);
		 
		 if(tv != null)
			 return new VijestiJSONWrapper("Success","", tv);
		 else
			 return new VijestiJSONWrapper("Error","Trazena vijest ne postoji", null);
	}
	
	@Override
	public VijestiJSONWrapper updatevijesti(Long id, vijesti v){
		vijesti x;
		
		if((x = vijestirepo.findOne(id)) == null)
			return new VijestiJSONWrapper("Error","Vijest ne postoji!", null);
		
		if(v.getAutorID()!=null && kc.checkKorisnik(v.getAutorID())==false)
		{
			return new VijestiJSONWrapper("Error","Uneseni korisnik ne postoji!",null);
		}
		x.setAutorID(v.getAutorID() != null ? v.getAutorID() : x.getAutorID());
		x.setDatum(v.getDatum() != null ? v.getDatum() : x.getDatum());
		x.setTextvVijesti(v.getTextVijesti() != null ? v.getTextVijesti() : x.getTextVijesti());
		x.setNazivVijesti(v.getNazivVijesti() != null ? v.getNazivVijesti() : x.getNazivVijesti());
		x.setAktivan(v.getAktivan() != null ? v.getAktivan() : x.getAktivan());

		return new VijestiJSONWrapper("Success","", vijestirepo.save(x));
		
	}
	
	@Override
	public Boolean deletevijesti(Long id){
		vijesti x = null;
		
		if((x = vijestirepo.findOne(id) ) == null)
			return false;
		
		x.setAktivan(0);
		
		vijestirepo.save(x);
		
		return true;
	}
	
	@Override
	public List<vijesti> getVijestii(){
		return vijestirepo.findAll();
	}
	
	@Override
	public List<vijesti> getAktivVijestii(){
		return vijestirepo.findAktivne();
	}
	
	@Override
	public tipvijestiJSONWrapper gettipvijesti(long id){
		
		vijesti x = null;
		
		if((x = vijestirepo.findOne(id)) == null)
			return new tipvijestiJSONWrapper("Error","Tip vijesti ne postoji", null);
		
		
		return new tipvijestiJSONWrapper("Success", "", x.getTipVijesti());
	}
	
	@Override
	public KategorijeVijestiJSONWrapper getkategorijavijesti (long id){
vijesti x = null;
		
		if((x = vijestirepo.findOne(id)) == null)
			return new KategorijeVijestiJSONWrapper("Error","Tip vijesti ne postoji", null);
		
		
		return new KategorijeVijestiJSONWrapper("Success", "", x.getKategorijaVijesti());
	}
	
	@Override
	public VijestiListJSONWrapper getVijestKorisnik(Long id){
		if(kc.checkKorisnik(id)==false)
		{
			return new VijestiListJSONWrapper("Error","Uneseni korisnik ne postoji!",null);
		}
		List<vijesti> v = vijestirepo.findByAutorID(id);
		
		if (v==null){
			return new VijestiListJSONWrapper("Success","Korisnik nije napisao ni jednu vijest!",null);
		}
		return new VijestiListJSONWrapper("Success","",v);
	}
	
}
