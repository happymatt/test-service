package com.jhqc.pxsj.concurrent;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.amazonaws.annotation.GuardedBy;
import com.google.common.collect.Lists;
import com.jhqc.pxsj.concurrent.MyTask.Cat;

public class NoOrderMain {
	
	private static boolean status = false;
	private static int num = 0;
	private final static BigInteger integer = new BigInteger("100");
	
	public static void main(String[] args) {
		List<String> list = Lists.newArrayList("1", "2", "3");
		List<String> synList = Collections.synchronizedList(list);
		synList.add("4");
		System.out.println(synList.get(3));
		System.out.println(integer.intValue());
		System.out.println(integer.intValue());
		Hashtable<String, String> tableHashtable;
		ScheduledThreadPoolExecutor stpe;
	}

	static class MyThread extends Thread {

		@Override
		public void run() {
			while(!status){
				Thread.yield();
			}
			System.out.println(num);
			num = 0;
		}
	}
	static class Pig {
		//使用私有锁
		private final Object myLock = new Object();
		@GuardedBy("myLock")
		private Cat cat = new Cat();
		public void run() {
			synchronized (myLock) {
				cat.sayHello();
			}
		}
	}
}
