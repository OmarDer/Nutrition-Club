package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitandsit.Model.tipvijesti;
import com.fitandsit.Repository.TipVijestiRepo;

import jsonwrappers.tipvijestiJSONWrapper;

@Service
public class TipVijestiService implements TipVijestiInterface {

	TipVijestiRepo tipvijestirepo;
	
	@Autowired
	public void setTipVijestiRepo(TipVijestiRepo tipvijestirepo) {
		this.tipvijestirepo = tipvijestirepo;
	}
	
	@Override
	public tipvijestiJSONWrapper createtipvijesti(tipvijesti tv){
		if (tv.getTipID()!=null && tipvijestirepo.findOne(tv.getTipID()) != null)
			return new tipvijestiJSONWrapper("Error", "Tip vijesti već postoji!", tv);
		
		return new tipvijestiJSONWrapper("Success", "", tipvijestirepo.save(tv));
	}
	
	@Override
	public tipvijestiJSONWrapper gettipvijesti(Long id){
		 tipvijesti tv = tipvijestirepo.findOne(id);
		 
		 if(tv != null)
			 return new tipvijestiJSONWrapper("Success","", tv);
		 else
			 return new tipvijestiJSONWrapper("Error","Trazeni tip vijesti ne postoji", null);
	}
	
	@Override
	public tipvijestiJSONWrapper updatetipvijesti(Long id, tipvijesti tv){
		tipvijesti x;
		
		if((x = tipvijestirepo.findOne(id)) == null)
			return new tipvijestiJSONWrapper("Error","Tip vijesti ne postoji!", null);
		
		x.setNazivTipa(tv.getNazivTipa() != null ? tv.getNazivTipa() : x.getNazivTipa());
		x.setArhiviran(tv.getArhiviran() != null ? tv.getArhiviran() : x.getArhiviran());

		return new tipvijestiJSONWrapper("Success","", tipvijestirepo.save(x));
		
	}
	
	@Override
	public Boolean deletetipvijesti(Long id){
		
		tipvijesti x = null;
		
		if((x = tipvijestirepo.findOne(id) ) == null)
			return false;
		
		x.setArhiviran(1);
		
		tipvijestirepo.save(x);
		
		return true;
	}
	
	@Override
	public List<tipvijesti> gettipvijestii(){
		return tipvijestirepo.findAll();
	}
}
