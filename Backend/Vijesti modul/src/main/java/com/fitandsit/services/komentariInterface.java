package com.fitandsit.services;

import java.util.List;

import com.fitandsit.Model.komentari;
import com.fitandsit.jsonwrappers.KomentariJSONWrapper;
import com.fitandsit.jsonwrappers.KomentariListJSONWrapper;
import com.fitandsit.jsonwrappers.VijestiJSONWrapper;

public interface komentariInterface {
	KomentariJSONWrapper createKomentar(komentari k);
	KomentariJSONWrapper getKomentar(Long id);
	KomentariJSONWrapper updateKomentar(Long id, komentari k);
	Boolean deleteKomentar(Long id);
	List<komentari> getKomentari();
	List<komentari> getAktivKomentari();
	VijestiJSONWrapper getvijesti(Long id);
	KomentariJSONWrapper getchildkomentar(Long id);
	KomentariListJSONWrapper getKomentarKorisnik(Long id);
}
