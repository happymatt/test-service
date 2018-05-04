package com.jhqc.pxsj.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class LinkedHashMapTest {
	
	public static void main(String[] args) {
		LinkedHashMap<String, String> lmap = new LinkedHashMap<String, String>();
		HashMap<String, String> hmap = new HashMap<String, String>();
		
		UUID uid = UUID.randomUUID();
		System.out.println(uid);
		
		lmap.put("tom1", "tomvalue");
		hmap.put("tom1", "tomvalue");
		
		lmap.put("tom2", "tomvalue");
		hmap.put("tom2", "tomvalue");
		
		lmap.put("matt1", "mattvalue");
		hmap.put("matt1", "mattvalue");
		
		lmap.put("matt2", "mattvalue");
		hmap.put("matt2", "mattvalue");
		
		lmap.put("jack1", "jackvalue");
		hmap.put("jack1", "jackvalue");
		
		lmap.put("jack2", "jackvalue");
		hmap.put("jack2", "jackvalue");
		Set<Entry<String, String>> lset = lmap.entrySet();
		Set<Entry<String, String>> hset = hmap.entrySet();
		System.out.println(hmap.get("jack"));
	}

}
