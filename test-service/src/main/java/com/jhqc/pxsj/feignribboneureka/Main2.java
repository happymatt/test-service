package com.jhqc.pxsj.feignribboneureka;

import java.io.IOException;

import org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient;

import com.jhqc.pxsj.feignribboneureka.IIn.DefultIn;
import com.jhqc.pxsj.feignribboneureka.IOut.DefaultOut;
import com.netflix.client.AbstractLoadBalancerAwareClient;

import feign.Client;
import feign.Request;
import feign.Request.Options;
import feign.Response;

public class Main2 {
	
	public static void main(String[] args) {
		okhttp3.Request.Builder builder =new okhttp3.Request.Builder();
		//AbstractLoadBalancerAwareClient<S, T> albc;
		//TraceLoadBalancerFeignClient fd;
		LoadBalancerFeignClient lbfc;
		
	}
	
	static class CustClient implements Client {

		@Override
		public Response execute(Request request, Options options)
				throws IOException {
			return null;
		}
		
	}

}
class DefaultOperation<I extends IIn, O extends IOut> implements IOperation<I,O> {

	@Override
	public O excute(I i) {
		Oim<O> oim = new Oim<O>();
		return oim.getT();
	}
	
}

interface IOperation<I extends IIn, O extends IOut> {
	O excute(I i);
}


interface IIn {
	IOut in();
	static class DefultIn implements IIn {

		@Override
		public IOut in() {
			return null;
		}
	}
}

class Oim<T extends IOut> {
	T t;

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
}
interface IOut {
	
	void out(String out);
	
	static class DefaultOut<T extends IOut> implements IOut {

		@Override
		public void out(String out) {
			System.out.println("this is" + out);
		}
		
	}
	
}