package com.jhqc.pxsj.collection;

import java.util.TreeMap;

public class TreeMapTest {
	
	public static void main(String[] args) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put("a", "10");
		map.put("b", "20");
		map.put("c", "20");
		System.out.println(map.get("a"));
		System.out.println(map.get("b"));
	}

}
