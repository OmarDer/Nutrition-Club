package jsonwrappers;

import com.fitandsit.Model.kategorijavijesti;

public class KategorijeVijestiJSONWrapper {
	private String status;
	private String msg;
	private kategorijavijesti kategorijavijest;
	
	
	public KategorijeVijestiJSONWrapper(String status, String msg, kategorijavijesti kategorijavijest) {
		super();
		this.status = status;
		this.msg = msg;
		this.kategorijavijest = kategorijavijest;
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
	public kategorijavijesti getkategorijavijest() {
		return kategorijavijest;
	}
	public void setkategorijavijest(kategorijavijesti kategorijavijest) {
		this.kategorijavijest = kategorijavijest;
	}
}
