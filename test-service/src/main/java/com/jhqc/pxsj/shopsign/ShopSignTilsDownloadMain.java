package com.jhqc.pxsj.shopsign;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.jhqc.pxsj.shopsign.common.Constants;

/**
 * 下载til信息
 */
public class ShopSignTilsDownloadMain {
	
	private static int scount = 0;
	private static int fcount = 0;
	
	public static void main(String[] args) throws Exception {
		File file = new File(Constants.TIL_DIR + Constants.PACKAGE_VERSION + ".txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String url = null;
		BufferedWriter bw = new BufferedWriter(new FileWriter(Constants.TIL_DOWNLOAD_ERROR + Constants.PACKAGE_VERSION + ".txt"));
		while ((url = br.readLine()) != null) {
			Thread.sleep(50);
			 if (downloadTilFile(url)) {
				 if (scount % 200 == 0) {
					System.out.println("已下载成功" + scount + "个");
				}
				 scount ++;
			 } else {
				 fcount ++;
				 bw.write(url + "\n");
				 bw.flush();
				 System.out.println("下载失败:" + url );
			 }
		}
		br.close();
		bw.close();
		System.out.println("成功：" + scount + "个");
		System.out.println("失败：" + fcount + "个");
	}
	
	private static boolean downloadTilFile(String urlStr) {
		 InputStream is = null;
		 OutputStream os = null;
		try {
			URL url = new URL(urlStr);
		    // 打开连接
		    URLConnection con = url.openConnection();
		    // 超时设为10秒
		    con.setConnectTimeout(10000);
		    con.setReadTimeout(10000);
		    // 输入流
		     is = con.getInputStream();
		    // 1K的数据缓冲
		    byte[] bs = new byte[2048];
		    // 读取到的数据长度
		    int len;
		    // 输出的文件流
		    String tilInfo = parseTilInfo(urlStr);
		    File file = new File(Constants.TIL_DATA_DIR);
		    if (!file.exists())  {
		    	file.mkdirs();
		    }
		    os = new FileOutputStream(Constants.TIL_DATA_DIR + String.format("\\%s.txt", tilInfo));
		    // 开始读取
		    while ((len = is.read(bs)) != -1) {
		      os.write(bs, 0, len);
		    }
		    return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				os.close();
			    is.close();
			} catch (Exception e2) {
			}
			
		}
		
		 	
		    
	}
	
	private static String parseTilInfo(String urlStr) {
		return urlStr.substring(urlStr.lastIndexOf("/") + 1, urlStr.lastIndexOf("."));
	}

}
