package com.jhqc.pxsj.ribbon;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.mapper.Mapper;

public class RibbonMain {
	static AtomicInteger ai = new AtomicInteger(0);
	static int size = 8;
	
	static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) throws JsonProcessingException {
		LoadBalancerClient lbc;
		LoadBalancerInterceptor lbBalancerInterceptor;
		LoadBalancerAutoConfiguration lbac;
		
		System.out.println(mapper.writeValueAsString(new NameAndAgeImpl()));
		System.out.println(test());
		System.out.println(test());
		System.out.println(test());
		System.out.println(test());
		System.out.println(test());
		Exception exception;
	}
	
	public static int test() {
		int current = ai.get();
		if (current >= size) {
			ai.set(0);
			return ai.get();
		}
		return ai.incrementAndGet();
	}

}
