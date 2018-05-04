package com.jhqc.pxsj;

import java.util.Map;

import org.springframework.core.env.Environment;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;

public class Main3 {
	
	public static void main(String[] args) {
		
//		/System.setProperty("AWS_SECRET_ACCESS_KEY", "ZUAHOMJFVEQYADIVQKHB");
		Map<String, String> pros = System.getenv();
		for (Map.Entry<String, String> entry : pros.entrySet()) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		 EnvironmentVariableCredentialsProvider env;
		 
		 SystemPropertiesCredentialsProvider dd;
		 
		System.out.println(Region.getRegion(Regions.US_WEST_2)
			    .isServiceSupported(AmazonDynamoDB.ENDPOINT_PREFIX));
		
		
	}

}
