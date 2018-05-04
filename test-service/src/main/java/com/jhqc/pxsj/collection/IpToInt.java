package com.jhqc.pxsj.collection;

public class IpToInt {
	
	public static void main(String[] args) {
		String ipStr = "192.168.1.213";
		System.out.println(ipToInt(ipStr));
	}
	
	public static long ipToInt(String ipStr) {
		String[] arr = ipStr.split("\\.");
		if (arr.length != 4) {
			throw new RuntimeException("wrong ip");
		}
		long m = 0;
		for (int i = 0; i < 4; i++) {
			m |= Long.parseLong(arr[i]) << (24 - i * 8);
		}
		return m;
	}

}
