package com.jhqc.pxsj.concurrent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main2 {
	
	public static void main(String[] args) {
		
		User user = new User();
		int hashCode = user.hashCode();
		System.out.println(Integer.toBinaryString(hashCode));
		System.out.println(Integer.toHexString(hashCode));
		System.out.println(user.hashCode());
		
		String temp = getTemp(0);
		String temp2 = temp;
		temp = new String("123");
		System.out.println(temp2);
	
		String[] strs = new String[16];
		System.out.println(strs[0]);
		
		String key1 = new String("abmc");
		String key2 = new String("abmc");
		String key3 = "abmc";
		
		System.out.println(key1 == key2);
		System.out.println(key1 == key3);
		System.out.println(key3 == key2);
		Map<String, Integer> strMap = new HashMap<String, Integer>();
		strMap.put(key1, 123);
		strMap.put(key2, 235);
		
		System.out.println(strMap.get(key1));
		String key = "abc";
		int h = key.hashCode();
		System.out.println(Integer.toBinaryString(h));
		int m =  h  ^ (h >>> 16);
		System.out.println(Integer.toBinaryString(m));
		
	
		Map<String, Object> map = new HashMap<String, Object>();
		Object value = new Object();
		map.put("abc", value);
		map.put("abc", value);
		
		Set<String> set = new HashSet<String>();
		set.add("abc");
		set.add("abc");
		System.out.println(set.isEmpty());
		
		HashSet<String> hashSet;
		
	}
	public static class User {
		
	}
	public static String getTemp(int m) {
		if(m == 0) return null;
		return "";
	}
	public static int hash(String key) {
		 int h;
	     return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}
	
	public static void test() {
		String temp = "abcdefghijklmnopqrstuvwxyz";
		char[] chars = temp.toCharArray();
		for (int i = 0; i < 24; i++) {
			for (int j = i + 1; j < 26 - (i+1); j++) {
				for (int k = j + 1; k < 26- (j+1); k++) {
					System.out.println(""+ chars[i] + chars[j] + chars[k]);
				}
			}
		}
	}

}
