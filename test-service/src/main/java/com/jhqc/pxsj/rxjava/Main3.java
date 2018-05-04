package com.jhqc.pxsj.rxjava;

import com.netflix.loadbalancer.Server;

import rx.functions.Func1;
import rx.observables.BlockingObservable;

public class Main3 {
	public static void main(String[] args) {
		BlockingObservable<String> bo;
	}

}

class MyFunction<T> implements Func1<Server, Class<T>> {

	@Override
	public Class<T> call(Server t) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
