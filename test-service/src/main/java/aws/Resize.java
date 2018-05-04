package aws;

import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class Resize {
	
	private final static String filePath = "src/main/resources/resize/2-72.jpg";
	public static void main(String[] args) throws IOException {
		Thumbnails.of(new File(filePath))
    	.size(2000,1000)
        .outputQuality(1f)
        .toFile("src/main/resources/resize/output/result5.jpg");
		System.out.println("success");
	}

}
