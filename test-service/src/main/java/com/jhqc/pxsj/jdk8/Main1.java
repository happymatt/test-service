package com.jhqc.pxsj.jdk8;

import java.util.ArrayList;
import java.util.List;

public class Main1 {
	
	
	public static void main(String[] args) {
		int maxQps = 200;
		String[] rtList = "1,1,1,10,10".split(",");
		int requestNum = 5000;
		int threadNum = 10;
		long result = doneTime(maxQps, rtList, requestNum, threadNum);
		System.out.println(result);
		
		
		
		String st1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(st1.intern() == st1);
		
		String st2 = new StringBuilder("计算").append("机").toString();
		System.out.println(st2.intern() == st2);
		
		
		
		int length = 11;
		List<String> list = new ArrayList<String>(length);
		for (int i = 0; i < length; i++) {
			list.add(String.valueOf(i++).intern());
		}
		System.out.println("end");
	}

	
	static long doneTime(int maxQps,String[] rtList,int requestNum,int threadNum) {
        //TODO
        int qpsSum = 0;
        for (String rtString : rtList) {
            int singleMaxQps = threadNum * 1000 / Integer.valueOf(rtString);
            if (singleMaxQps > maxQps) {
                qpsSum += maxQps;
            }else {
                qpsSum += singleMaxQps;
            }
        }
 
        return requestNum / qpsSum * 1000;
    }
}
