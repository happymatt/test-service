package com.jhqc.pxsj.nio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
	
	private static String path = "F:\\test.java";
	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile(path, "rw");
		FileChannel channel = file.getChannel();
		FileInputStream f = new FileInputStream(new File(path));
		BufferedInputStream bis;
		byte[] b = new byte[1];
		f.read(b);
		f.close();
		ByteBuffer buffer = ByteBuffer.allocate(16);
		buffer.hasRemaining();
		//CharBuffer buf = CharBuffer.allocate(1024);
		int ret = channel.read(buffer);
		System.out.println(ret);
		int pos = buffer.position();
		System.out.println(pos);
		buffer.limit();
		buffer.capacity();
		
		buffer.reset();
		buffer.flip();
		byte bt = buffer.get();
		System.out.println((char) bt);
		buffer.clear();
		int ret2 = channel.read(buffer);
		System.out.println(ret2);
		int pos2 = buffer.position();
		System.out.println(pos2);
		file.close();
	}

}
