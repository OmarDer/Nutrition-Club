package com.fitandsit.jsonwrappers;

import java.util.List;

import com.fitandsit.Model.vijesti;

public class VijestiListJSONWrapper {
	private String status;
	private String msg;
	private List<vijesti> vijest;
	
	
	public VijestiListJSONWrapper(String status, String msg, List<vijesti> vijest) {
		super();
		this.status = status;
		this.msg = msg;
		this.vijest = vijest;
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
	public List<vijesti> getvijest() {
		return vijest;
	}
	public void setvijest(List<vijesti> vijest) {
		this.vijest = vijest;
	}
}
