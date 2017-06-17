package com.fitandsit.jsonwrappers;

import com.fitandsit.Model.tipvijesti;

public class tipvijestiJSONWrapper {
	private String status;
	private String msg;
	private tipvijesti tipvijest;
	
	
	public tipvijestiJSONWrapper(String status, String msg, tipvijesti tipvijest) {
		super();
		this.status = status;
		this.msg = msg;
		this.tipvijest = tipvijest;
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
	public tipvijesti gettipvijest() {
		return tipvijest;
	}
	public void settipvijest(tipvijesti tipvijest) {
		this.tipvijest = tipvijest;
	}
}
