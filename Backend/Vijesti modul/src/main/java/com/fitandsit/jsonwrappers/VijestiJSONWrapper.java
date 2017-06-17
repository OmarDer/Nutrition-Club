package com.fitandsit.jsonwrappers;

import com.fitandsit.Model.vijesti;

public class VijestiJSONWrapper {
	private String status;
	private String msg;
	private vijesti vijest;
	
	
	public VijestiJSONWrapper(String status, String msg, vijesti vijest) {
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
	public vijesti getvijest() {
		return vijest;
	}
	public void setvijest(vijesti vijest) {
		this.vijest = vijest;
	}
}
