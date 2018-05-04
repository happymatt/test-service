package com.jhqc.pxsj.shopsign.common;

public class ExceptionInfo {
	
	private String buildingId;
	private String shopId;
	private String msg;
	
	public ExceptionInfo(){}
	public ExceptionInfo(String buildingId,String shopId,String msg) {
		this.buildingId = buildingId;
		this.shopId = shopId;
		this.msg = msg;
		
	}
	
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
