package com.jhqc.pxsj.http;

public class HttpClientCallException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2529888291317846596L;
	
	public HttpClientCallException() {
		super();
	}
	
	public HttpClientCallException(String msg) {
		super(msg);
	}
	
	public HttpClientCallException(Throwable t) {
		initCause(t);
	}	
	
	public HttpClientCallException(String msg,Throwable t) {
		super(msg);
		initCause(t);
	}	
	

}
