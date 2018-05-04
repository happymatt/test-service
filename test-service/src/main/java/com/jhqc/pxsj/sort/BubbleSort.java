package com.jhqc.pxsj.sort;

/**
 * @author HuChao
 * 冒泡排序
 */
public class BubbleSort {
	
	
	private static int[] a = new int[] {
		1,2,3,4,5,6
	};
	private static int[] b = new int[] {
		1,2,3,4,5,6
	};
	
	public static void main(String[] args) {
		simpleBubbleSort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i] + ",");
		}
		System.out.println("==================");
		advancedBubbleSort(b);
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i] + ",");
		}
		
		
	}
	
	
	private static void simpleBubbleSort(int[] a) {
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - 1; j++) {
				count1 ++;
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					count2 ++ ;
				}
			}
		}
		System.out.println("循环了" + count1 + "次");
		System.out.println("比较成功" + count2 + "次");
	}
	
	
	private static void advancedBubbleSort(int[] a) {
		int j, k;
		int flag = a.length;// flag来记录最后交换的位置，也就是排序的尾边界

		while (flag > 0) {// 排序未结束标志
			k = flag; // k 来记录遍历的尾边界
			flag = 0;
			for (j = 1; j < k; j++) {
				if (a[j - 1] > a[j]) {// 前面的数字大于后面的数字就交换
					// 交换a[j-1]和a[j]
					int temp;
					temp = a[j - 1];
					a[j - 1] = a[j];
					a[j] = temp;
					// 表示交换过数据;
					flag = j;// 记录最新的尾边界.
				}
			}
		}
	}
	
	//这种方式优化力度不够
	private static void advancedBubbleSort2(int[] a) {
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				count1 ++;
				if (a[j] > a[j + 1]) {
					int temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					count2 ++ ;
				}
			}
		}
		System.out.println("循环了" + count1 + "次");
		System.out.println("比较成功" + count2 + "次");
	}

}
