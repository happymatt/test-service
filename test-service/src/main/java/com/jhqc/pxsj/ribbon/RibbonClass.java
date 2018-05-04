package com.jhqc.pxsj.ribbon;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.netflix.ribbon.RibbonClientConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.eureka.DomainExtractingServerList;
import org.springframework.cloud.netflix.ribbon.eureka.EurekaRibbonClientConfiguration;

import com.netflix.loadbalancer.AbstractLoadBalancer;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.ClientConfigEnabledRoundRobinRule;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PredicateBasedRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;
import com.netflix.niws.loadbalancer.DiscoveryEnabledNIWSServerList;

//
/**
 * ribbon loadbalancer 相关类
 * @author HuChao
 */
public class RibbonClass {
	
	  public static void main(String[] args) {
	        SpringApplication.run(RibbonClass.class, args);
	    }
	    
	    public static void test() {
	    	ILoadBalancer ilb;
	    	AbstractLoadBalancer alb;
	    	BaseLoadBalancer blb;
	    	DynamicServerListLoadBalancer<MyServer> dslb;
	    	ZoneAwareLoadBalancer<MyServer> zalb;
	    	
	    	ServerList<MyServer> serverList;
	    	
	    	DiscoveryEnabledNIWSServerList discoveryServerList;
			DomainExtractingServerList des;
	    	
	    	EurekaRibbonClientConfiguration ercc;

	    	IRule ir;
	    	AbstractLoadBalancerRule albr;
	    	RoundRobinRule rrrRobinRule;
	    	RandomRule rr;
	    	RetryRule reRule;
	    	WeightedResponseTimeRule wrtr;
	    	
	    	ClientConfigEnabledRoundRobinRule ccerrr;
	    	BestAvailableRule bar;
	    	PredicateBasedRule pbr;
	    	AvailabilityFilteringRule afr;
	    	ZoneAvoidanceRule zar;
	    	
	    	
	    	LoadBalancerClient lbc;
	    	RibbonLoadBalancerClient rlbc;
	    	LoadBalancerAutoConfiguration abac;
	    	LoadBalancerInterceptor lbi;
	    	
	    	RibbonClientConfiguration rcc;
	    	Optional<MyServer> opt = Optional.empty();
	    	if (opt.isPresent()) {
				
			}
	    	
	     }
	}

	class Dog {
		
	}
	class MyServer extends Server {

		public MyServer(String id) {
			super(id);
			// TODO Auto-generated constructor stub
		}
		
}
