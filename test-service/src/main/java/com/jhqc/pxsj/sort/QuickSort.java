package com.jhqc.pxsj.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSort {

	//private static int[] a = {6, 3, 4, 7, 2, 8, 0, 9, 1, 5};
	private static int[] a = {6,3,4,7,5};

	public static void main(String[] args) {
		sort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));

	}

	private static void sort(int a[], int left, int right) {
		if (left < right) {//判断是否继续划分
			// Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
			int i = left, j = right, x = a[left];
			while (i < j) {//前后遍历是否碰头
				while (i < j && a[j] >= x) {// 从右向左找第一个小于x的数
					j--;
				}
				if (i < j) {
					a[i++] = a[j];
				}
				while (i < j && a[i] < x) {// 从左向右找第一个大于等于x的数
					i++;
				}
				if (i < j) {
					a[j--] = a[i];
				}
			}
			a[i] = x;
			sort(a, left, i - 1); // 递归调用
			sort(a, i + 1, right);
		}
	}

	private static int[] sort(int[] a) {
		int pivot = a[0];
		List<Integer> small = new ArrayList<Integer>();
		List<Integer> big = new ArrayList<Integer>();

		for (int i = 1; i < a.length; i++) {
			if (a[i] <= pivot) {
				small.add(a[i]);
			} else {
				big.add(a[i]);
			}
		}
		small.add(pivot);
		small.addAll(big);
		for (int i = 0; i < small.size(); i++) {
			a[i] = small.get(i);
		}
		return a;
	}

}
