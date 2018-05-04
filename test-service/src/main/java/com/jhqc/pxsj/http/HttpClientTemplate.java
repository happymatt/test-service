package com.jhqc.pxsj.http;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

public class HttpClientTemplate {
	
	
	
	public Object excute(HttpClientCallBack callBack) {
		try {
			MultipartFile mf;
			WebApplicationContext wac;
			return null;
		} catch (Exception e) {
			throw new HttpClientCallException(e);
		}
	}

}
