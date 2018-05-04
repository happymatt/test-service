package com.jhqc.pxsj.reference;

import java.lang.ref.WeakReference;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Apple apple = new Apple("bigapple");
		WeakReference<Apple> ref = new WeakReference<Apple>(apple);
		Boy boy = new Boy(apple);
		/*apple = null;
		System.out.println(boy.getApple().getName());
		boy = null;*/
		
		while(true) {
			System.out.println(boy);
			if (ref.get() != null) {
				System.out.println("not");
			} else {
				System.out.println("yes");
				break;
			}
		}
		
		
	}
}

class Apple {
	private String name;
	
	public Apple(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
class Boy {
	private Apple apple;
	public Boy(Apple apple) {
		this.apple = apple;
	}
	public Apple getApple() {
		return apple;
	}
	public void setApple(Apple apple) {
		this.apple = apple;
	}
	
}
