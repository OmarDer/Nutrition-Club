package jsonwrappers;

import com.fitandsit.Model.komentari;

public class KomentariJSONWrapper {
	private String status;
	private String msg;
	private komentari komentar;
	
	
	public KomentariJSONWrapper(String status, String msg, komentari komentar) {
		super();
		this.status = status;
		this.msg = msg;
		this.komentar = komentar;
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
	public komentari getkomentar() {
		return komentar;
	}
	public void setkomentar(komentari komentar) {
		this.komentar = komentar;
	}
}
