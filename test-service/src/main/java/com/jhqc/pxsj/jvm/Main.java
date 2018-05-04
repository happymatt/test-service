package com.jhqc.pxsj.jvm;



public class Main {
	
	
	public static void main(String[] args) {
		int j = 0;
		for (int i = 0; i < 100; i++) {
			j = ++j;
		}
		System.out.println(j);
		//System.out.println(Arrays.toString(new Fruit<BigCar>().getClass().getTypeParameters()));
		
	}
	
}

class Fruit<E extends BigCar> {
	
}
