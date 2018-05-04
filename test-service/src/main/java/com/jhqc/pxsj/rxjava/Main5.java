package com.jhqc.pxsj.rxjava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;

public class Main5 {
	
	public static void main(String[] args) throws InterruptedException {
		if (compare(0, 0)) {
			System.out.println("return null");
		} else {
			System.out.println("dadasf");
		}
		
		if (compare(1, 2)) {
			System.out.println("return null");
		} 
		
		Observable<Long> cold = Observable.interval(200, TimeUnit.MILLISECONDS);

		cold.subscribe(i -> System.out.println("First: " + i));
		Thread.sleep(500);
		cold.subscribe(i -> System.out.println("Second: " + i));
		
		Future<String> f = new Future<String>() {

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isCancelled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isDone() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String get() throws InterruptedException, ExecutionException {
				Thread.sleep(5000);
				return "helloworld";
			}

			@Override
			public String get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException,
					TimeoutException {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	private static Boolean compare(int x, int y) {
		if (x == 0 || y == 0) {
			return null;
		}
		return x >= y;
	}

}
