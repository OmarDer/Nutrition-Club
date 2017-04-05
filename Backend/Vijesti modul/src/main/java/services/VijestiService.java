package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitandsit.Model.vijesti;
import com.fitandsit.Repository.VijestiRepo;

import jsonwrappers.KategorijeVijestiJSONWrapper;
import jsonwrappers.VijestiJSONWrapper;
import jsonwrappers.tipvijestiJSONWrapper;

@Service
public class VijestiService implements VijestiInterface {
	
	VijestiRepo vijestirepo;
	
	@Autowired
	public void setVijestiRepo(VijestiRepo vijestirepo) {
		this.vijestirepo = vijestirepo;
	}
	
	@Override
	public VijestiJSONWrapper createvijesti(vijesti v){
		if (v.getVijestID()!=null && vijestirepo.findOne(v.getVijestID()) != null)
			return new VijestiJSONWrapper("Error", "Vijesti već postoji!", v);
		
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
		
		x.setAutorID(v.getAutorID() != null ? v.getAutorID() : x.getAutorID());
		x.setDatum(v.getDatum() != null ? v.getDatum() : x.getDatum());
		x.setTextvVijesti(v.getNazivVijesti() != null ? v.getNazivVijesti() : x.getNazivVijesti());
		x.setNazivVijesti(v.getNazivVijesti() != null ? v.getNazivVijesti() : x.getNazivVijesti());
		x.setArhiviran(v.getArhiviran() != null ? v.getArhiviran() : x.getArhiviran());

		return new VijestiJSONWrapper("Success","", vijestirepo.save(x));
		
	}
	
	@Override
	public Boolean deletevijesti(Long id){
		vijesti x = null;
		
		if((x = vijestirepo.findOne(id) ) == null)
			return false;
		
		x.setArhiviran(1);
		
		vijestirepo.save(x);
		
		return true;
	}
	
	@Override
	public List<vijesti> getVijestii(){
		return vijestirepo.findAll();
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
}
