package com.jhqc.pxsj.concurrent;

import java.util.TimerTask;

import com.jhqc.pxsj.concurrent.MyTask.Cat;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Thread thread = new MyThread();
		thread.start();
		
		thread.interrupt();

		Cat cat = new Cat();
		System.out.println(cat.getM());
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				cat.sayHello();
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				cat.run();
			}
		});
		t1.start();
		Thread.sleep(1000);
		t2.start();
	}

}

class MyThread extends Thread {
	@Override
	public void run() {
		super.run();
		while (true) {
			System.out.println("gagag");
		}
	}
}

class MyTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("task run()");
	}

	class ClacThread implements Runnable {

		@Override
		public void run() {

		}

	}
	
	public static class Animal {
		private int m = 100;
		public synchronized void sayHello() {
			System.out.println("animal sayHello...");
		}
		public synchronized void run() {
			System.out.println("animal run...");
		}
		
		private void fuck() {
			System.out.println("animal fuck");
		}
		
		public int getM() {
			return this.m;
		}
	}

	public static class Cat extends Animal{
		@Override
		public synchronized void sayHello() {
			try {
				System.out.println("start to sleep....");
				Thread.sleep(5000);
				System.out.println("end sleep....");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("cat sayHello...");
		    super.sayHello();
		}
		

		
	}
	
	

}
