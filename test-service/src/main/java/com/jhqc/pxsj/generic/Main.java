package com.jhqc.pxsj.generic;

import java.util.ArrayList;
import java.util.List;




public class Main<T>{
	
	public static void main(String[] args) {
		
/*		List<? extends Fruit> list = new ArrayList<>();
		List<?> list2 = new ArrayList<Apple>();
		List<? super Apple> list3 = new ArrayList<Fruit>();
		list.add(new Apple());
		list.add(new Fruit());
		list2.add(new Apple());
		list3.add(new Apple());
		list3.add(new Fruit());*/
		
		List mm = new ArrayList();
		mm.add("123");
		mm.add(1);
		mm.add(new Object());
		System.out.println(mm.get(0));
		
		
	}
	public void test() {
	}
	
	public  void print(T t) {
		System.out.println(t);
	}

}

class Fruit{}
class Apple extends Fruit{}
class Orange extends Fruit{}
