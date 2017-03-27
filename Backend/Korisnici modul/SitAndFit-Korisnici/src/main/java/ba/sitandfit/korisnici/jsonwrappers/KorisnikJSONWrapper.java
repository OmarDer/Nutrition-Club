package ba.sitandfit.korisnici.jsonwrappers;

import ba.sitandfit.korisnici.model.Korisnik;

public class KorisnikJSONWrapper {
	
	private String msg;
	private Korisnik korisnik;
	
	
	public KorisnikJSONWrapper(String msg, Korisnik korisnik) {
		super();
		this.msg = msg;
		this.korisnik = korisnik;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	
	

}
