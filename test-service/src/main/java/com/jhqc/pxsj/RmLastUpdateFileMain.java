package com.jhqc.pxsj;

import java.io.File;

public class RmLastUpdateFileMain {
	
	
	private static String win_pro_path = "E:\\mvn-rep-test";
	private static String win_pro_path2 = "E:\\mvn-repository";
	private static int count  = 0;
	
	public static void main(String[] args) {
		File file = new File(win_pro_path2);
		deleteFile(file);
		System.out.println("======已删除"+count+"个lastUpdate文件");
	}
	
	private static void deleteFile(File file){
		if (file.isFile()) {
			if (file.getName().endsWith("lastUpdated")) {
				boolean bl = file.delete();
				if (bl) {
					System.out.println("删除"+file.getName()+"成功");
					count ++;
				} else {
					System.out.println("删除"+file.getName()+"失败");
				}
			}
		} else if (file.isDirectory()) {
			File fs[] = file.listFiles();
			for (File f : fs) {
				deleteFile(f);
			}
		}
	}
	
}
