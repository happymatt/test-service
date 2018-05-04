package com.jhqc.pxsj.shopsign.domain;

import java.util.List;

public class BuildingFileContent {
	
	private List<BuildingInfo> buildings;
	private List<ShopInfo> shop_signs;

	public List<BuildingInfo> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<BuildingInfo> buildings) {
		this.buildings = buildings;
	}

	public List<ShopInfo> getShop_signs() {
		return shop_signs;
	}

	public void setShop_signs(List<ShopInfo> shop_signs) {
		this.shop_signs = shop_signs;
	}
	
	

}
