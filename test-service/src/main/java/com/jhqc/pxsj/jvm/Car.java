package com.jhqc.pxsj.jvm;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


public class Car {
	
	
	static {
		System.out.println("static initial");
	}
	{
		System.out.println("not static initial");
	}

	public static void main(String args[]) throws InterruptedException {
		int length = 10000000;
		
		List<Dog> list = new ArrayList<Car.Dog>();
		for (int i = 0; i < length; i++) {
			Thread.sleep(1);
			Dog dog = (Dog) Proxy.newProxyInstance(Dog.class.getClassLoader(), 
					new Class[] {Dog.class}, new InvocationHandler() {
						
						@Override
						public Object invoke(Object proxy, Method method, Object[] args)
								throws Throwable {
							System.out.println("before eatFood");
							return null;
						}
					});
			dog.eatFood();
			list.add(dog);
		}
		
		List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
		for (GarbageCollectorMXBean b : l) {
			System.out.println(b.getName());
		}
	}
	
	static interface Dog {
		void eatFood();
	}

}
