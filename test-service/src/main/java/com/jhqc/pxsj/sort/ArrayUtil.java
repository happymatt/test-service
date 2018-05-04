package com.jhqc.pxsj.sort;



public class ArrayUtil {
	
	public final static int a[] = new int[] {
		6,2,5,4,3,1,9,7,0,10,8,11
	};
	
	public static void printAll(int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (i == 0) {
				System.out.print("{");
			}
			System.out.print(a[i]);
			if (i == a.length - 1) {
				System.out.print("}");
			} else {
				System.out.print(",");
			}
		}
	}

}
