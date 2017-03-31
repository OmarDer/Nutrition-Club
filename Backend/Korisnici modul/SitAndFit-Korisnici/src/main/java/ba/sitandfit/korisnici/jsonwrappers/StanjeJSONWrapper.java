package ba.sitandfit.korisnici.jsonwrappers;

import ba.sitandfit.korisnici.model.Stanje;

public class StanjeJSONWrapper {
	
	private String status;
	private String msg;
	private Stanje stanje;
	
	
	public StanjeJSONWrapper(String status, String msg, Stanje stanje) {
		super();
		this.status = status;
		this.msg = msg;
		this.stanje = stanje;
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
	public Stanje getStanje() {
		return stanje;
	}
	public void setStanje(Stanje stanje) {
		this.stanje = stanje;
	}
	
	

}
