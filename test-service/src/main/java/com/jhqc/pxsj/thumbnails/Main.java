package com.jhqc.pxsj.thumbnails;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		String filePath = "F:\\images\\test.jpg";
		String outPutPath = "F:\\images\\output\\test2.jpg";
    	
    	Thumbnails.of(filePath)
    		.scale(0.2f)
    		.outputQuality(0.5f)
    		.toFile(outPutPath);
    	System.out.println("end");
		
	}

}
