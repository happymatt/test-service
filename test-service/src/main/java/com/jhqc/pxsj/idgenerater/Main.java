package com.jhqc.pxsj.idgenerater;

import java.util.Date;

public class Main {
	
	public static void main(String[] args) {
		long m = 0x0000000000000000L;
		long current = new Date().getTime();
		System.out.println("current ms:" + current);
		long m2 = current << 20;
		System.out.println("left move 20 :" + m2);
		long max = Long.MAX_VALUE;
	}

}
