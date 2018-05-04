package com.jhqc.pxsj.sort;

/**
 * @author HuChao 选择排序，每次循环选择最小的值交换循环起始位置的值
 */
public class SelectSort {

	public static void main(String[] args) {
		selectSort(ArrayUtil.a);
		ArrayUtil.printAll(ArrayUtil.a);

	}

	public static void selectSort(int[] a) {

		int min_index;
		for (int i = 0; i < a.length - 1; i++) {
			min_index = i;
			for (int j = i + 1; j < a.length; j++)
				// 每次扫描选择最小项
				if (a[j] < a[min_index])
					min_index = j;
			if (min_index != i)// 找到最小项交换，即将这一项移到列表中的正确位置
			{
				int temp;
				temp = a[i];
				a[i] = a[min_index];
				a[min_index] = temp;
			}
		}
	}

}
