package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitandsit.Model.kategorijavijesti;
import com.fitandsit.Repository.KategorijeVijestiRepo;

import jsonwrappers.KategorijeVijestiJSONWrapper;

@Service
public class KategorijeVijestiService implements KategorijeVijestiInterface {

	KategorijeVijestiRepo kategorijevijestirepo;
	
	@Autowired
	public void setKategorijeVijestiRepo(KategorijeVijestiRepo kategorijevijestirepo) {
		this.kategorijevijestirepo = kategorijevijestirepo;
	}
	
	@Override
	public KategorijeVijestiJSONWrapper createkategorijevijesti(kategorijavijesti kv){
		if (kv.getKategorijaID() != null && kategorijevijestirepo.findOne(kv.getKategorijaID()) != null)
			return new KategorijeVijestiJSONWrapper("Error", "Kategorija vijesti već postoji!", kv);
		
		return new KategorijeVijestiJSONWrapper("Success", "", kategorijevijestirepo.save(kv));
	}
	
	@Override
	public KategorijeVijestiJSONWrapper getkategorijevijesti(Long id){
		kategorijavijesti x = kategorijevijestirepo.findOne(id);
		 
		 if( x != null)
			 return new KategorijeVijestiJSONWrapper("Success","", x);
		 else
			 return new KategorijeVijestiJSONWrapper("Error","Trazena kategorija vijesti ne postoji", null);
	}
	
	@Override
	public KategorijeVijestiJSONWrapper updatekategorijevijesti(Long id, kategorijavijesti kv){
		kategorijavijesti x;
		if((x= kategorijevijestirepo.findOne(id)) == null)
			return new KategorijeVijestiJSONWrapper("Error", "Kategorija vijesti ne postoji", null);
		
		x.setNazivTipa(kv.getImeKategorije() != null ? kv.getImeKategorije() : x.getImeKategorije());
		x.setAktivan(kv.getAktivan() != null ? kv.getAktivan() : x.getAktivan());
		
	   return new KategorijeVijestiJSONWrapper("Succcess", "", kategorijevijestirepo.save(x));
		
	}
	
	
	@Override
	public Boolean deletevijesti(Long id){
		kategorijavijesti kv = null;
		if((kv=kategorijevijestirepo.findOne(id)) ==null)
			return false;
		kv.setAktivan(0);
		kategorijevijestirepo.save(kv);
		return true;
	}
	
	
	@Override
	public List<kategorijavijesti> getKategorijeVijesti(){
		return kategorijevijestirepo.findAll();
	}
	
	@Override
	public List<kategorijavijesti> getAktivKategorijeVijesti(){
		return kategorijevijestirepo.findAktivne();
	}
	
}
