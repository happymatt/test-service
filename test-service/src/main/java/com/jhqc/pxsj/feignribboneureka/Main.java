package com.jhqc.pxsj.feignribboneureka;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;


public class Main {
	
	public static void main(String[] args) {
		String m1 = "1.2";
		System.out.println(StringUtils.isNumeric(m1));
		FeignClientsConfiguration fcc;
		Main m = new Main();
		InnerClass ic = m.new InnerClass();
		
		ic.setName("i am inner class");
		
	}
	private void say() {
		InnerClass ic = new InnerClass();
	}

	class InnerClass {
		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
}
