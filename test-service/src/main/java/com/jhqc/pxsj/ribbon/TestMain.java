package com.jhqc.pxsj.ribbon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

public class TestMain {
	
	public static void main(String[] args) {
		List<?> list = Lists.newArrayList();
		list = null;
		System.out.println((list = Lists.newArrayList("123","245")) instanceof List);
		System.out.println(list.getClass().equals(ArrayList.class));
		System.out.println(test(list));
		System.out.println(list);
	}
	
	public static boolean test(Object o) {
		return (o instanceof Collection);
	}

}
