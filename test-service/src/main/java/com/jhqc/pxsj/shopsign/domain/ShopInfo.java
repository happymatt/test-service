package com.jhqc.pxsj.shopsign.domain;

import java.util.List;

public class ShopInfo {
	
	 private String prefabType;
	 private Long cellNum;
	 private String shopId; //"4432483", 
     private String buildingId;// "028001001a01", 
     private String shopName;// "天虎冒菜", 
     private String shopCode;// "17", 
     private String picUrl;// "http://artistwork.pek3a.qingstor.com/filecrc/zp028001001006.jpg_1935828451", 
     private List<String> point_list;
    /* "point_list": [
         "-50.122,2.794,-2282.541", 
         "-50.122,3.502,-2282.541", 
         "-53.719,3.502,-2288.083", 
         "-53.719,2.794,-2288.083"
     ]*/
     
	public String getPrefabType() {
		return prefabType;
	}
	public void setPrefabType(String prefabType) {
		this.prefabType = prefabType;
	}
	public Long getCellNum() {
		return cellNum;
	}
	public void setCellNum(Long cellNum) {
		this.cellNum = cellNum;
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
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public List<String> getPoint_list() {
		return point_list;
	}
	public void setPoint_list(List<String> point_list) {
		this.point_list = point_list;
	}
     
}
