package com.jhqc.pxsj.shopsign.common;

import java.util.List;

public class ShopSignPackageReq {
	
		
	private String name;// = "028001001a01"; //该tag的唯一标识, user 端可以作为pc order
	private String type;// = "shop";//处理类型
	private String city;// = "0280";//城市ID
	private String bundleCrc;// = "10000";//版本号
	private String bundleName;// = "028001001a01"; //打包后bundle文件名
	private List<String> platforms;// = Lists.newArrayList("android",
									// "windows");//请求的需要打包的平台
	private List<String> depends;// = Lists.newArrayList();

	// 根据字段信息拼接打包请求地址
	private String reqUrl;

	public String getReqUrl() {
		return reqUrl;
	}

	public void setReqUrl(String reqUrl) {
		this.reqUrl = reqUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBundleCrc() {
		return bundleCrc;
	}

	public void setBundleCrc(String bundleCrc) {
		this.bundleCrc = bundleCrc;
	}

	public String getBundleName() {
		return bundleName;
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

	public List<String> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(List<String> platforms) {
		this.platforms = platforms;
	}

	public List<String> getDepends() {
		return depends;
	}

	public void setDepends(List<String> depends) {
		this.depends = depends;
	}

}
