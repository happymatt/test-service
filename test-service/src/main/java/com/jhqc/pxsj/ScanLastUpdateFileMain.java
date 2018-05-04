package com.jhqc.pxsj;

import java.io.File;
import java.io.FilenameFilter;

public class ScanLastUpdateFileMain {
	
	private static String win_pro_path = "E:\\mvn-rep-test";
	private static String win_pro_path2 = "E:\\mvn-repository";
	private static int count  = 0;
	
	public static void main(String[] args) {
		File file = new File(win_pro_path2);
		scanFile(file);
		System.out.println("======扫描出"+count+"个lastUpdateFile文件");
	}
	
	private static void scanFile1(File file){
		File files[] =file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.contains("zookeeper")) {
					return true;
				}
				return false;
			}
		});
		for (File f : files) {
			if (!f.isDirectory()) {
				count ++;
				System.out.println(f);
			} else {
				scanFile1(f);
			}
		}
	}
	
	private static void scanFile(File file){
		if (file.isFile()) {
			if (file.getName().endsWith("lastUpdated")) {
				System.out.println(file.getName());
				count ++;
			}
		} else if (file.isDirectory()) {
			File fs[] = file.listFiles();
			for (File f : fs) {
				scanFile(f);
			}
		}
	}

}
