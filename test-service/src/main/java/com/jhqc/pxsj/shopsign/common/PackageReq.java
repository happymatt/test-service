package com.jhqc.pxsj.shopsign.common;

public class PackageReq {
	
	private String buildingId;
	private String url;
	public PackageReq(){}
	public PackageReq(String buildingId, String url){
		this.buildingId = buildingId;
		this.url = url;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
