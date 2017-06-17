package com.fitandsit.services;

import java.util.List;

import com.fitandsit.Model.vijesti;
import com.fitandsit.jsonwrappers.KategorijeVijestiJSONWrapper;
import com.fitandsit.jsonwrappers.VijestiJSONWrapper;
import com.fitandsit.jsonwrappers.VijestiListJSONWrapper;
import com.fitandsit.jsonwrappers.tipvijestiJSONWrapper;

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
