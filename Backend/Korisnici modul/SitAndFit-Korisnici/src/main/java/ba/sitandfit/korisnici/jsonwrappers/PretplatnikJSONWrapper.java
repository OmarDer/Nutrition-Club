package ba.sitandfit.korisnici.jsonwrappers;


import ba.sitandfit.korisnici.model.Pretplatnik;

public class PretplatnikJSONWrapper {
	
	private String status;
	private String msg;
	private Pretplatnik pretplatnik;
	
	
	public PretplatnikJSONWrapper(String status, String msg, Pretplatnik pretplatnik) {
		super();
		this.status = status;
		this.msg = msg;
		this.pretplatnik = pretplatnik;
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
	public Pretplatnik getPretplatnik() {
		return pretplatnik;
	}
	public void setPretplatnik(Pretplatnik pretplatnik) {
		this.pretplatnik = pretplatnik;
	}
	
	

}
