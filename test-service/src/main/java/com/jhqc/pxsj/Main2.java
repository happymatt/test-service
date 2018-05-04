package com.jhqc.pxsj;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Main2 {
	public static void main(String[] args) throws CloneNotSupportedException,
			IOException {
		Man man1 = new Man("man1");
		SingletonMan sgm = SingletonMan.getInstantce();
		sgm.sayHello();
		try {
			RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
			FileChannel inChannel = aFile.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(48);
			//buffer.compareTo(buffer);

			int bytesRead = inChannel.read(buffer);
			while (bytesRead != -1) {

				System.out.println("Read " + bytesRead);
				buffer.flip();

				while (buffer.hasRemaining()) {
					System.out.print((char) buffer.get());
				}

				buffer.rewind();
				buffer.hasRemaining();
				buffer.clear();
				buffer.compact();
				bytesRead = inChannel.read(buffer);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Man man2 = (Man) man1.clone();

		System.out.println(man2.getName());
		System.out.println(man1);
		System.out.println(man2);
		System.out.println(man1 == man2);
		String a = "123";
		String b = "123";
		Object o1 = new Object();
		Object o2 = new Object();
		System.out.println(o1);
		System.out.println(o2);

		o1 = o2;
		System.out.println(o1);
		System.out.println(o2);
		boolean result = Objects.equals(a, b);
		System.out.println(result);

		HashMap<?, ?> map;
		HashSet<?> set;

		Integer a1 = 1250;
		Integer a2 = 1250;
		System.out.println(a1 == a2);

		String temps = "1,2,3,4";
		String[] tempArray = temps.split(",");
		List<String> list = new ArrayList<String>(tempArray.length);
		for (String temp : tempArray) {
			list.add(temp);
		}
		Iterator<String> ite = list.iterator();
		for (; ite.hasNext();) {
			String next = ite.next();
			if ("2".equals(next)) {
				ite.remove();
			}

		}
		// list.set(1, "6");
		for (String string : list) {
			// System.out.println(string);
		}

		String[] newArray = new String[list.size()];
		list.toArray(newArray);

		List<String> ll = Arrays.asList(newArray);
		for (String string : ll) {
			System.out.println(string);
		}

	}

}

class Man implements Cloneable {

	private String name;
	private Man man;

	public Man() {
	}

	public Man(String name) {
		this.name = name;
		man = new Man();
		man.setName(this.name + "man");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Man getMan() {
		return man;
	}

	public void setMan(Man man) {
		this.man = man;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}

class SingletonMan {

	private static final SingletonMan INSTANCE = new SingletonMan();

	private SingletonMan() {
	}

	public static SingletonMan getInstantce() {
		return INSTANCE;
	}

	public void sayHello() {
		System.out.println("hello boy!");
	}

}
