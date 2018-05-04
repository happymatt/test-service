package com.jhqc.pxsj.lintcode;

public class Main {
	
	public static void main(String[] args) {
		int m =-32;
		int n =7;
		System.out.println(adividb(m, n));
	}
	
	
	private static int aplusb(int a, int b) {
		int m = a ^ b;//按位相加，不取进位
		int n = (a & b) << 1;//按位相加，只取进位
		while (n != 0) {//前两步循环，直到按位相加进位为0
			int c = m;
			m = m ^ n;
			n = (c & n) << 1;
		}
		return m;
	}
	
	private static int aminusb(int a, int b) {
		int m = ~b;//取反  正数取反 直接变负数
		int n = aplusb(m, 1);//+1
		return aplusb(a, n);
	}
	
	private static int amultiplyb(int a, int b) {
		if (a == 0 || b == 0) {
			return 0;
		}
		int m = 0;
		int absa = a > 0 ? a: aplusb(~a, 1);//a绝对值
		int absb = b > 0 ? b : aplusb(~b, 1);//b绝对值
		boolean negative = (a > 0 && b < 0) || (a < 0 && b > 0);//结果是否为负数
		
		for (int i = 0; i < absb; i++) {//循环累加
			m = aplusb(m, absa);
		}
		if (negative) {
			return aplusb(~m, 1);
		}
		return m;
	}
	
	private static int adividb(int a, int b) {
		if (b == 0) {
			throw new RuntimeException("被除数不能为0");
		}
		if (a == 0) {
			return 0;
		}
		int absa = a > 0 ? a: aplusb(~a, 1);//a绝对值
		int absb = b > 0 ? b : aplusb(~b, 1);//b绝对值
		if (absa < absb) {
			return 0;
		}
		
		int m = absa - absb;
		int count = 0;
		while (m >= 0) {
			count ++;
			m = m - absb;
		}
		boolean negative = (a > 0 && b < 0) || (a < 0 && b > 0);//结果是否为负数
		if (negative) {
			return aplusb(~count, 1);
		}
		return count;
	}

}
