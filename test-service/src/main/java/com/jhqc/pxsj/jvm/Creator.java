package com.jhqc.pxsj.jvm;

public class Creator<T> {
	
	private T data;
	
	
	public void resolve(T data) {
		
	}
	
	public <G extends T> void make(G data) {
		
	}
	public <E> void fill(E data) {
		
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

}
