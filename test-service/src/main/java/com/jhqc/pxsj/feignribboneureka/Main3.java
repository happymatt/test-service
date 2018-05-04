package com.jhqc.pxsj.feignribboneureka;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

import org.joda.time.format.DateTimeFormat;
import org.springframework.util.StopWatch;


public class Main3 {
	
	public static void main(String[] args) {
		//DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		StopWatch sw = new StopWatch();
		sw.start("1");
		test1();
		sw.stop();
		System.out.println(sw.prettyPrint());
		StopWatch sw2 = new StopWatch();
		sw2.start("2");
		test2();
		sw2.stop();
		System.out.println(sw2.prettyPrint());
		System.out.println("================");
		final CountDownLatch latch = new CountDownLatch(1);

		InterruptedException iException;
		DefaultRule dr = new DefaultRule();
		DefaultPredicate<DefaultRule> dp = new DefaultPredicate<DefaultRule>();
		if (dp.apply(dr)) {
			System.out.println(true);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("123").append("123")
			.append("ddd")
			.append("ddd")
			.append("ddd")
			.append(123);
	}
	
	private static void test1() {
		String temp = "temp";
		for (int i = 0; i < 100000; i++) {
			temp += "123";
		}
		System.out.println(temp.length());
	}
	
	private static void test2() {
		StringBuilder temp = new StringBuilder("temp");
		for (int i = 0; i < 10000000; i++) {
			temp.append("123");
		}
		System.out.println(temp.length());
	}

}


interface Predicate<T> {
	boolean apply(T t);
}

class DefaultPredicate<G extends Rule> implements Predicate<G> {

	@Override
	public boolean apply(G t) {
		return t.check();
	}
}

interface Rule {
	boolean check();
}

class DefaultRule implements Rule {

	@Override
	public boolean check() {
		return false;
	}
	
}
