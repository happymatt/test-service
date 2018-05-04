package com.jhqc.pxsj.dp;

public interface Person {
	
	
	@Mapping(path = "http://192.168.31.242:8302/v1/shop/queryShopByShopId?shopId=300", method = "GET")
	String run();

}
