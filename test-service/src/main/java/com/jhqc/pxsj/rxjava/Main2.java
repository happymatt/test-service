package com.jhqc.pxsj.rxjava;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class Main2 {
	public static class Car {
		private String color;
		
		public Car() {}
		
		public Car(String color) {
			this.color = color;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
		
	}
	public static void main(String[] args) {
		Observable.create(new OnSubscribe<Integer>() {
			@Override
			public void call(Subscriber<? super Integer> t) {
				System.out.println("io thread is running,and sleep 5 s");
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				t.onNext(500);
			}
			
		}).subscribeOn(Schedulers.io())
				.observeOn(Schedulers.computation())
				.subscribe(new Action1<Integer>() {
					@Override
					public void call(Integer t) {
						for (int i = 0; i < t; i++) {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(i + "computation");
						}
					}
				});
		
		
		Action1<String> a = new Action1<String>() {
			@Override
			public void call(String color) {
				System.out.println(color + "gege");
			}
		};
		Subscription sub = Observable.just(new Car("red"))
				.map(new Func1<Car, String>() {
					@Override
					public String call(Car car) {
						return car.getColor();
					}
				}).subscribe(a);
		if (sub.isUnsubscribed()) {
			sub.unsubscribe();
		}
	}
}
