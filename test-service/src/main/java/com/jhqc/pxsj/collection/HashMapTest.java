package com.jhqc.pxsj.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.common.collect.Lists;



public class HashMapTest {

	public static void main(String[] args) throws InterruptedException {
		
		
		
		
		
		Object o = new Object();
		
		Object o2 = o;
		System.out.println(o2 == o);
		
		ConcurrentHashMap<String, String> cmap = new ConcurrentHashMap<String, String>();
		List<String> list = Lists.newArrayList("tom");
		ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<String>(list);
		String element = clq.peek();
		System.out.println(element);
		cmap.put("abc", "123");
		String result = cmap.get("abc");
		System.out.println(result);
		Map<String, String> map = new HashMap<>();
		int length = 10000000;
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < length; i++) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
							System.out.println("s");
						}
					}, "ftf" + i).start();
				}
			}
		});
		t.start();
		t.join();
	}

}

class MyThread implements Runnable {

	private Map<String, String> map;

	public MyThread(Map<String, String> map) {
		this.map = map;
	}

	@Override
	public void run() {

		System.out.println("yes");
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

}
