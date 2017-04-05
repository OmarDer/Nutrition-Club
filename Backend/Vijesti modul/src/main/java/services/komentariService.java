package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitandsit.Model.komentari;
import com.fitandsit.Repository.KomentariRepo;

import jsonwrappers.KomentariJSONWrapper;
import jsonwrappers.VijestiJSONWrapper;

@Service
public class komentariService implements komentariInterface {

	KomentariRepo komentarrepo;
	
	@Autowired
	public void setKomentarRepo(KomentariRepo komentarrepo) {
		this.komentarrepo = komentarrepo;
	}
	
	@Override
	public KomentariJSONWrapper createKomentar(komentari k){
		if (k.getKomentarID()!=null && komentarrepo.findOne(k.getKomentarID()) != null)
			return new KomentariJSONWrapper("Error", "Komentar već postoji!", k);
		
		return new KomentariJSONWrapper("Success", "", komentarrepo.save(k));
	}
	
	@Override
	public KomentariJSONWrapper getKomentar(Long id){
		komentari k =komentarrepo.findOne(id);
		 
		 if(k != null)
			 return new KomentariJSONWrapper("Success","", k);
		 else
			 return new KomentariJSONWrapper("Error","Trazeni komentar ne postoji", null);
	}
	
	@Override
	public KomentariJSONWrapper updateKomentar(Long id, komentari k){
		komentari x;
		
		if((x = komentarrepo.findOne(id)) == null)
			return new KomentariJSONWrapper("Error","Komentar ne postoji!", null);
		
		
		x.setAutorID(k.getAutorID() != null ? k.getAutorID() : x.getAutorID());
		x.setImaRoditelja(k.getImaRoditelja() != null ? k.getImaRoditelja() : x.getImaRoditelja());
		x.setTextKomentara(k.getTextKomentara() != null ? k.getTextKomentara() : x.getTextKomentara());
		x.setArhiviran(k.getArhiviran() != null ? k.getArhiviran() : x.getArhiviran());

		return new KomentariJSONWrapper("Success","", komentarrepo.save(x));
	}
	
	@Override
	public Boolean deleteKomentar(Long id){
		komentari x = null;
		
		if((x = komentarrepo.findOne(id) ) == null)
			return false;
		
		x.setArhiviran(1);
		
		komentarrepo.save(x);
		
		return true;
	}
	
	@Override
	public List<komentari> getKomentari(){
		return komentarrepo.findAll();
	}
	
	@Override
	public VijestiJSONWrapper getvijesti(Long id){
komentari x = null;
		
		if((x = komentarrepo.findOne(id)) == null)
			return new VijestiJSONWrapper("Error","Vijest ne postoji", null);
		
		
		return new VijestiJSONWrapper("Success", "", x.getVijesti());
	}
	
	@Override
	public KomentariJSONWrapper getchildkomentar(Long id){
		komentari x = null;
		
		if((x = komentarrepo.findOne(id)) == null)
			return new KomentariJSONWrapper("Error","Komentar ne postoji", null);
		
		
		return new KomentariJSONWrapper("Success", "", x.getRoditelj());
	}
}
