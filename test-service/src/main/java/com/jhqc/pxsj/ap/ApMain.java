package com.jhqc.pxsj.ap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.beans.factory.FactoryBean;

public class ApMain {
	
	public static void main(String[] args) {
		ProxyFactory pf = new ProxyFactory(new Dog());
		NameMatchMethodPointcutAdvisor advisor =new NameMatchMethodPointcutAdvisor();
		advisor.setMappedNames("run","say");
		advisor.setAdvice(new MethodInterceptor() {
			
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				System.out.println("before");
				Object obj =invocation.proceed();
				System.out.println("after");
				return obj;
			}
		});
		pf.addAdvisor(advisor);
		Dog ob = (Dog) pf.getProxy();
		ob.run();
		ob.say();
		AopProxy ap;
		ProxyFactoryBean pfb = new ProxyFactoryBean();
		FactoryBean<Man> fb;
		BeanNameAutoProxyCreator bna;
		DefaultAdvisorAutoProxyCreator daapc;
		SingletonTargetSource sts;
		
	}

}

