package com.jhqc.pxsj.collection;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.FutureTask;

import com.google.common.collect.Lists;

public class QueueTest {
	public static void main(String[] args) {
		String temp = "abcdefghijklmn";
		char[] chars = temp.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = chars.length -1; i >= 0; i--) {
			sb.append(chars[i]);
		}
		System.out.println(sb.toString());
		
		Pig pig = new Pig("littlepig");
		Pig pig2 = pig;
		pig2.setName("bigpig");
		List<String> list = Lists.newArrayList("tom", "jack");
		List<String> list2 = list;
		list2.add("jms");
		ConcurrentLinkedQueue<String> clq = new ConcurrentLinkedQueue<String>();
		clq.offer("matt");
		clq.offer("matt2");
		clq.offer("matt3");
		clq.offer("matt4");
		String e1 = clq.peek();
		System.out.println(e1);
		String e2 = clq.poll();
		System.out.println(e2);
		FutureTask<String> ft;
		//ft.get();
	}

}

class Pig {
	private String name;
	public Pig(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
