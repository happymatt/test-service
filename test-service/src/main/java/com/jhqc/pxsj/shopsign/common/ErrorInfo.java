package com.jhqc.pxsj.shopsign.common;


public class ErrorInfo {
	
	private PackageReq req;
	private String msg;
	
	public ErrorInfo(){}
	public ErrorInfo(PackageReq req, String msg) {
		this.req = req;
		this.msg = msg;
		
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public PackageReq getReq() {
		return req;
	}
	public void setReq(PackageReq req) {
		this.req = req;
	}
	

}
