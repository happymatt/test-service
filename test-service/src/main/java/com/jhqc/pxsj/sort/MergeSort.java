package com.jhqc.pxsj.sort;

import java.util.Arrays;

public class MergeSort {

	private static int[] arr = ArrayUtil.a;

	private static int[] a = {1,3,5,7,9};
	private static int[] b = {2,4,6,8,10};
	public static void main(String[] args) {
		
		sort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
		
		int[] c = new int[a.length + b.length];
		simpleMerge(a, a.length, b, b.length, c);

	}

	public static int[] sort(int[] a, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			sort(a, low, mid);
			sort(a, mid + 1, high);
			// 左右归并
			merge(a, low, mid, high);
		}
		return a;
	}

	public static void merge(int[] a, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		// 把较小的数先移到新数组中
		while (i <= mid && j <= high) {
			if (a[i] < a[j]) {
				temp[k++] = a[i++];
			} else {
				temp[k++] = a[j++];
			}
		}
		// 把左边剩余的数移入数组
		while (i <= mid) {
			temp[k++] = a[i++];
		}
		// 把右边边剩余的数移入数组
		while (j <= high) {
			temp[k++] = a[j++];
		}
		// 把新数组中的数覆盖nums数组
		for (int x = 0; x < temp.length; x++) {
			a[x + low] = temp[x];
		}
	}

	/**
	 * @param a
	 * @param n
	 * @param b
	 * @param m
	 * @param c
	 * 将两个有序数组a，b合并到c中
	 */
	private static void simpleMerge(int a[], int n, int b[], int m, int c[]) {
		int i, j, k;

		i = j = k = 0;
		while (i < n && j < m) {
			if (a[i] < b[j]) {
				c[k++] = a[i++];
			} else {
				c[k++] = b[j++];
			}
		}
		while (i < n) {
			c[k++] = a[i++];
		}

		while (j < m) {
			c[k++] = b[j++];
		}
	}

}
