package com.jhqc.pxsj.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Main3 {

	public static void main(String[] args) throws IOException {
		
		SocketChannel sc;
		ByteBuffer buffer = ByteBuffer.allocate(8);
		
	    Socket socket;
	    InputStream is;
		
		String content = "abcdefgh";
		buffer.put(content.getBytes());
		System.out.println("capacity:" + buffer.capacity());
		System.out.println("limit:" + buffer.limit());
		System.out.println("position:" + buffer.position());
		System.out.println("after flip -------");
		buffer.flip();
		System.out.println("capacity:" + buffer.capacity());
		System.out.println("limit:" + buffer.limit());
		System.out.println("position:" + buffer.position());
		
		
		
		System.out.println("after read to file 1.txt ------");
		for (int i = 0; i < 8; i++) {
			System.out.println((char) buffer.get());
			System.out.println("position:" + buffer.position());
		}
//		System.out.println("capacity:" + buffer.capacity());
//		System.out.println("limit:" + buffer.limit());
//		System.out.println("position:" + buffer.position());
		
	
	}
}
