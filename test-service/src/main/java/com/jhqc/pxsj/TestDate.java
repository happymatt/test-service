package com.jhqc.pxsj;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
	static SimpleDateFormat sdfBig  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static SimpleDateFormat sdfSmall  = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(sdfBig.format(date));
		System.out.println(sdfSmall.format(date));
	}

}
