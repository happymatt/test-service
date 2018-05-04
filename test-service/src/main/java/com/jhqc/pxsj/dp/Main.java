package com.jhqc.pxsj.dp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Main {
	
	public static void main(String[] args) {
		Class<?>[] classes = {Person.class};
		Person man = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), classes, new MyInvocationHandler());
		String temp = man.run();
		System.out.println(temp);
	}
	static class MyInvocationHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			Mapping mp = method.getAnnotation(Mapping.class);
			String path = mp.path();
			return path;
		}
		
	}
}
