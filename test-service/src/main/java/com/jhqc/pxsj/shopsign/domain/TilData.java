package com.jhqc.pxsj.shopsign.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TilData {
	
	
	@JsonProperty("bundleName")
	private String buildingId;
	private List<AtlaInfo> atlas;
	
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public List<AtlaInfo> getAtlas() {
		return atlas;
	}
	public void setAtlas(List<AtlaInfo> atlas) {
		this.atlas = atlas;
	}

}
