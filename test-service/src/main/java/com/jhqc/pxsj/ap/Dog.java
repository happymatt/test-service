package com.jhqc.pxsj.ap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dog {
	
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(1495433468000L);
		System.out.println(sdf.format(date));
	}
	public void run() {
		System.out.println("dog run");
	}
	
	public void say() {
		System.out.println("dog say");
	}

}
