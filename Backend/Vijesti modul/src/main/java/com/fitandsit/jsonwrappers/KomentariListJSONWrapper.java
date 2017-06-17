package com.fitandsit.jsonwrappers;

import java.util.List;

import com.fitandsit.Model.komentari;

public class KomentariListJSONWrapper {

	private String status;
	private String msg;
	private List<komentari> komentar;
	
	
	public KomentariListJSONWrapper(String status, String msg, List<komentari> komentar) {
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
	public List<komentari> getkomentar() {
		return komentar;
	}
	public void setkomentar(List<komentari> komentar) {
		this.komentar = komentar;
	}
}
