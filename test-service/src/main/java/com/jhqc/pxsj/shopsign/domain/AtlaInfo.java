package com.jhqc.pxsj.shopsign.domain;

public class AtlaInfo {
	
	private String rect;// "0,0,0.5,0.25",
    private String atlasName;// "atlas_1.png",
    private String atlasType;// ".jpg",
    private String tile;// "zp028001001005.jpg"
	public String getRect() {
		return rect;
	}
	public void setRect(String rect) {
		this.rect = rect;
	}
	public String getAtlasName() {
		return atlasName;
	}
	public void setAtlasName(String atlasName) {
		this.atlasName = atlasName;
	}
	public String getAtlasType() {
		return atlasType;
	}
	public void setAtlasType(String atlasType) {
		this.atlasType = atlasType;
	}
	public String getTile() {
		return tile;
	}
	public void setTile(String tile) {
		this.tile = tile;
	}
}
