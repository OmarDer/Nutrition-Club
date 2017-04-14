package services;

import java.util.List;

import com.fitandsit.Model.vijesti;

import jsonwrappers.KategorijeVijestiJSONWrapper;
import jsonwrappers.VijestiJSONWrapper;
import jsonwrappers.VijestiListJSONWrapper;
import jsonwrappers.tipvijestiJSONWrapper;

public interface VijestiInterface {
	VijestiJSONWrapper createvijesti(vijesti v);
	VijestiJSONWrapper getvijesti(Long id);
	VijestiJSONWrapper updatevijesti(Long id, vijesti v);
	Boolean deletevijesti(Long id);
	List<vijesti> getVijestii();
	List<vijesti> getAktivVijestii();
	tipvijestiJSONWrapper gettipvijesti(long id);
	KategorijeVijestiJSONWrapper getkategorijavijesti (long id);
	VijestiListJSONWrapper getVijestKorisnik(Long id);
}
