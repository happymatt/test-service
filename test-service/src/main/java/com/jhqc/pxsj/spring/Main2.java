package com.jhqc.pxsj.spring;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;

public class Main2 {
	
	public static void main(String[] args) {
		
		Person p = (Person)Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[] {Person.class}, 
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						System.out.println("before say hello");
						System.out.println("invoke method");
						System.out.println("after say hello");
						return proxy;
					}
				});
		p.sayHello();
		
		Pointcut pc;
		MethodMatcher mm;
		
		AopProxy ap;
		
		DefaultAopProxyFactory dapf;
		
		ProxyFactory pf;
		
	}
	
	public static interface Person {
		void sayHello();
	}
}
