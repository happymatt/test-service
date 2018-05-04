package com.jhqc.pxsj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public class ReadFileMain {

	private static String filePath = "F:\\BaiduYunDownload\\jd3.txt";
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		URI uri = new URI("http://local_host:8889");
		System.out.println(uri);
		FileInputStream fis = new FileInputStream(new File(filePath));
		byte bts []=new byte[4096];
		fis.read(bts, 0, 4096);
		String str = new String(bts,Charset.defaultCharset());
		System.out.println(str);
		fis.close();
	}

}
