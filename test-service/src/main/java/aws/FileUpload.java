package aws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.util.StringUtils;

public class FileUpload {
	
	private final static String appId = "AKIAOLNGAHTCV5LCGEDA";
	private final static String secretyKey = "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";
	private final static String bucketName = "image.pxsj.com";
	
	public static void main(String[] args) throws IOException {
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(appId, secretyKey);
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.CN_NORTH_1)
                .withClientConfiguration(new ClientConfiguration().withProtocol(Protocol.HTTP))
                .build();
		String fileName = "result5.jpg";
		String filePath = "src/main/resources/upload/" + fileName;
		ObjectMetadata metadata = new ObjectMetadata();
		InputStream is = new FileInputStream(filePath);
		
		metadata.setContentLength(is.available());
		String contentType = Files.probeContentType(Paths.get(filePath));
		metadata.setContentType(contentType);
		
		StringBuilder keyName = new StringBuilder("3dcity/user/")
		        .append(new Date().getTime())
				.append(RandomUtil.getRandomKey())
				.append(fileName.substring(fileName.lastIndexOf(".")));
		PutObjectRequest request = new PutObjectRequest(bucketName, keyName.toString(), is, metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		PutObjectResult putResult = s3Client.putObject(request);
		if (!StringUtils.isNullOrEmpty(putResult.getETag())) {
			System.out.println(keyName);
		} else {
			System.out.println("upload fail!");
		}
		
	}

}
