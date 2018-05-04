package com.jhqc.pxsj.shopsign.domain;

public class BuildingInfo {

	private String guid;// "028001001002",
	private String fileCrc;// "1092997109",
	private String fileName;// "028001001002.fbx",
	private String type;// "fbx",
	private String postion;// "(-52.7, -0.3, -2277.5)",
	private String rotation;// "(0.0, 0.0, 0.0, 1.0)",
	private String scale;// "(1.0, 1.0, 1.0)",
	private String update_at;// "2017-05-01 02:20:18 +0800",
	private String bd_name;// "",
	private String bd_type;// "",
	private String bd_streetID;// "",
	private boolean bd_isImportant;// false

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getFileCrc() {
		return fileCrc;
	}

	public void setFileCrc(String fileCrc) {
		this.fileCrc = fileCrc;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPostion() {
		return postion;
	}

	public void setPostion(String postion) {
		this.postion = postion;
	}

	public String getRotation() {
		return rotation;
	}

	public void setRotation(String rotation) {
		this.rotation = rotation;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}

	public String getBd_name() {
		return bd_name;
	}

	public void setBd_name(String bd_name) {
		this.bd_name = bd_name;
	}

	public String getBd_type() {
		return bd_type;
	}

	public void setBd_type(String bd_type) {
		this.bd_type = bd_type;
	}

	public String getBd_streetID() {
		return bd_streetID;
	}

	public void setBd_streetID(String bd_streetID) {
		this.bd_streetID = bd_streetID;
	}

	public boolean isBd_isImportant() {
		return bd_isImportant;
	}

	public void setBd_isImportant(boolean bd_isImportant) {
		this.bd_isImportant = bd_isImportant;
	}

}
