package ba.sitandfit.korisnici.jsonwrappers;

import ba.sitandfit.korisnici.model.Rola;

public class RolaJSONWrapper {
	
	private String status;
	private String msg;
	private Rola rola;
	
	
	public RolaJSONWrapper(String status, String msg, Rola rola) {
		super();
		this.status = status;
		this.msg = msg;
		this.rola = rola;
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
	public Rola getRola() {
		return rola;
	}
	public void setRola(Rola rola) {
		this.rola = rola;
	}
	
	
}
