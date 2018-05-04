package com.jhqc.pxsj.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author HuChao 基数排序
 */
public class RadixSort {

	private static int[] a = {51,15,26,37,35,15};
	private static int[] c = {67, 94, 65, 2, 90, 8, 70, 2, 47, 78, 67, 75, 65, 97, 27, 56, 21, 75, 67, 55, 16, 93, 52, 57, 29, 51, 90, 53, 95, 93, 40, 68, 81, 91, 63, 11, 47, 30, 96, 80, 35, 22, 27, 62, 56, 7, 86, 74, 70, 52, 83, 66, 30, 41, 46, 18, 13, 36, 75, 32, 18, 35, 73, 6, 17, 1, 64, 82, 36, 57, 81, 80, 4, 85, 67, 84, 47, 77, 8, 20, 74, 84, 58, 63, 18, 33, 53, 68, 21, 26, 3, 52, 55, 1, 36, 2, 55, 40, 24, 76};
	public static void main(String[] args) {
		//int[] b = create(100);
		radixSort2(c, 100);
		sort(a, 6);
		System.out.println(Arrays.toString(a));
	}

	private static int[] create(int n) {
		int[] b = new int[n];
		Random r = new Random();
		for (int i = 0; i < b.length; i++) {
			b[i] = r.nextInt(100);
		}
		return b;
	}
 	public static void sort(int[] a, int n) {
		// 各位装通法
		int length = n;
		int divisor = 1;// 定义每一轮的除数，1,10,100...,用于分别取得个位数 十位数 百位数...
		int[][] bucket = new int[10][length];// 定义了10个桶，以防每一位都一样全部放入一个桶中
		int[] count = new int[10];// 统计每个桶中实际存放的元素个数
		int digit;// 获取元素中对应位上的数字，即装入那个桶

		for (int i = 1; i <= 3; i++) {// 经过3次装通操作，排序完成
			for (int temp : a) {// 依次用每位数上的值作为二维数组的外层index计算入桶
				digit = (temp / divisor) % 10;//分别取得个位数 十位数 百位数的值
				bucket[digit][count[digit]++] = temp;
			}
			int k = 0;// 被排序数组的下标
			for (int b = 0; b < 10; b++) {// 从0到9号桶按照顺序取出
				if (count[b] == 0)// 如果这个桶中没有元素放入，那么跳过
					continue;
				for (int w = 0; w < count[b]; w++) {
					a[k++] = bucket[b][w];
				}
				count[b] = 0;// 桶中的元素已经全部取出，计数器归零，做了这个处理就可以不用去清理bucket二维数组
			}
			divisor *= 10;
		}
	}
	
	//类似于基数排序，找出数组中第一个重复元素。  
	private static int radixSort2(int a[], int n) {
		int i;
		for (i = 0; i < n; i++) {
			while (i != a[i]) { 
				if (a[i] == a[a[i]])
					return a[i];
				swap(a, i, a[i]);
			}
		}
		return 0;
	}
	
	private static void swap(int[] a, int m, int n) {
		int temp = a[n];
		a[n] = a[m];
		a[m] = temp;
	}
}
