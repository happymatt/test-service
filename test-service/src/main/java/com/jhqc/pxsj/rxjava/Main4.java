package com.jhqc.pxsj.rxjava;

import java.util.List;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

import com.google.common.collect.Lists;

public class Main4 {
	
	public static void main(String[] args) {
		
		
		Observable<String> o = Observable.create(new OnSubscribe<String>() {

			@Override
			public void call(Subscriber<? super String> t) {
				String name = "this is a dog";
				t.onNext(name);
			}
		});
		o.subscribe(new Action1<String>() {

			@Override
			public void call(String t) {
				System.out.println(t);
			}
		});
		o.map(new Func1<String, Observable<String>>() {

			@Override
			public Observable<String> call(String t) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		o.flatMap(new Func1<String, Observable<String>>() {

			@Override
			public Observable<String> call(String t) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		o.concatMap(new Func1<String, Observable<? extends String>>() {

			@Override
			public Observable<? extends String> call(String t) {
				// TODO Auto-generated method stub
				return null;
			}
		});
		
		
		PublishSubject<String>  ps = PublishSubject.create();
		ps.subscribe(new Action1<String>() {
			@Override
			public void call(String t) {
				System.out.println("you send me the " + t);
				
			}
		});
		ps.onNext("man");
		Observable.create(sub -> {
			String t = "123";
			sub.onNext(t);
		}
		);
		
		Observable.just(Lists.newArrayList("a", "b", "c")).subscribe(new Action1<List<String>>() {

			@Override
			public void call(List<String> list) {
				for (String string : list) {
					System.out.println(string);
				}
			}
		});
		
	}

}
