package com.jhqc.pxsj.sort;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

public class ShellSort {
	
	public static void main(String[] args) {
		
		shellSort3(ArrayUtil.a);
		System.out.println(Arrays.toString(ArrayUtil.a));
		
	}
	
	public static void shellSort(int[] a) {
		 // 计算出最大的h值  
        int h = 1;  
        while (h <= a.length / 3) {  
            h = h * 3 + 1;  
        }  
        while (h > 0) {  
            for (int i = h; i < a.length; i += h) {  
                if (a[i] < a[i - h]) {  
                    int tmp = a[i];  
                    int j = i - h;  
                    while (j >= 0 && a[j] > tmp) {  
                        a[j + h] = a[j];  
                        j -= h;  
                    }  
                    a[j + h] = tmp;  
                }  
            }  
            // 计算出下一个h值  
            h = (h - 1) / 3;  
        }  
		
	}
	
	public static void shellSort2(int[] a) {
	        if(a == null || a.length <= 1){
	            return;
	        }
	        //增量
	        int incrementNum = a.length/2;
	        while(incrementNum >=1){
	            for(int i=0;i<a.length;i++){
	                //进行插入排序
	                for(int j=i;j<a.length-incrementNum;j=j+incrementNum){
	                    if(a[j]>a[j+incrementNum]){
	                        int temple = a[j];
	                        a[j] = a[j+incrementNum];
	                        a[j+incrementNum] = temple;
	                    }
	                }
	            }
	            //设置新的增量
	            incrementNum = incrementNum/2;
	        }
	    }
	
	/**
	 * shell排序
	 */
	private static void shellSort3(int a[]) {
		int gap = 1;
		int n = a.length;
        while (gap <= n / 3) {  
            gap = gap * 3 + 1;  
        }  

		while (gap > 0) {
			for (int i = gap; i < n; i++) {
				for (int j = i - gap; j >= 0 && a[j] > a[j + gap]; j -= gap) {
					swap(a,j, j + gap);
				}
			}
			// 计算出下一个gap值  
            gap = (gap - 1) / 3;  
		}
	}
	
	private static void swap(int[] a, int m, int n) {
		int temp = a[n];
		a[n] = a[m];
		a[m] = temp;
	}

}
