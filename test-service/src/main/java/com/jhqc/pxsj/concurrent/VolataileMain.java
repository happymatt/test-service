package com.jhqc.pxsj.concurrent;

import java.awt.Color;
import java.util.concurrent.LinkedTransferQueue;

public class VolataileMain {
	
	public static void main(String[] args) {
		LinkedTransferQueue<String> ltq;
		
	}

}

class Book {
	private volatile boolean borrowed;
	private String name;
	public boolean isBorrowed() {
		return borrowed;
	}
	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
