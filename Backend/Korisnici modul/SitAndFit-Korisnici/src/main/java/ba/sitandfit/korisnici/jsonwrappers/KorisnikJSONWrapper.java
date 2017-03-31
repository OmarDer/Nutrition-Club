package ba.sitandfit.korisnici.jsonwrappers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ba.sitandfit.korisnici.model.Korisnik;

public class KorisnikJSONWrapper {
	
	private String status;
	private String msg;
	private Korisnik korisnik;
	
	
	public KorisnikJSONWrapper(String status, String msg, Korisnik korisnik) {
		super();
		this.status = status;
		this.msg = msg;
		this.korisnik = korisnik;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
