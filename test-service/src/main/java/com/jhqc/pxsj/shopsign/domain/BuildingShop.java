package com.jhqc.pxsj.shopsign.domain;
import java.util.List;

public class BuildingShop {
	
	private String buildingId;
	
	private List<ShopInfo> shopInfos;
	
	
	public BuildingShop() {}
	public BuildingShop(String buildingId, List<ShopInfo> shopInfos) {
		this.buildingId = buildingId;
		this.shopInfos = shopInfos;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public List<ShopInfo> getShopInfos() {
		return shopInfos;
	}

	public void setShopInfos(List<ShopInfo> shopInfos) {
		this.shopInfos = shopInfos;
	}

}
