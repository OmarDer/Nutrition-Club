package services;

import java.util.List;

import com.fitandsit.Model.kategorijavijesti;

import jsonwrappers.KategorijeVijestiJSONWrapper;

public interface KategorijeVijestiInterface {
	KategorijeVijestiJSONWrapper createkategorijevijesti(kategorijavijesti kv);
	KategorijeVijestiJSONWrapper getkategorijevijesti(Long id);
	KategorijeVijestiJSONWrapper updatekategorijevijesti(Long id, kategorijavijesti kv);
	Boolean deletevijesti(Long id);
	List<kategorijavijesti> getKategorijeVijesti();
	List<kategorijavijesti> getAktivKategorijeVijesti();
}
