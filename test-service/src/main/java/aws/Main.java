package aws;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.HttpMethod;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jhqc.pxsj.common.ObjectResp;

public class Main {

	private static String appId = "AKIAOLNGAHTCV5LCGEDA";
	private static String secretyKey = "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";
	
	private static String id2 = "AKIAOUEBVVE57UDKOHVQ";
	private static String key2 = "TOtoyT0TJ35rJ46uTHYZPRRyoUMbhM9zTeNQihtP";

	public static void main(String[] args) {
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(appId, secretyKey);
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.CN_NORTH_1)
                .withClientConfiguration(new ClientConfiguration().withProtocol(Protocol.HTTP))
                .build();
		/*AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.build();*/
		
		String bucket = "image.pxsj.com";
		java.util.Date expiration = new java.util.Date();
		long msec = expiration.getTime();
		msec += 1000 * 60 * 60; // 1 hour.
		expiration.setTime(msec);
		             
		GeneratePresignedUrlRequest generatePresignedUrlRequest = 
		              new GeneratePresignedUrlRequest(bucket, "3dcity/user/info/15094350144506sDVTMedJR.png");
		generatePresignedUrlRequest.setMethod(HttpMethod.GET); // Default.
		generatePresignedUrlRequest.setExpiration(expiration);
		             
		URL s = s3Client.generatePresignedUrl(generatePresignedUrlRequest); 
		
		List<Bucket> buckets = s3Client.listBuckets();
		System.out.println("Your Amazon S3 buckets:");
		ListObjectsRequest request = new ListObjectsRequest();
		ObjectListing ol2 = s3Client.listObjects("basicresandroid", "Scene/0280/");
		
		for (S3ObjectSummary s3o : ol2.getObjectSummaries()) {
			System.out.println(s3o.getKey());
		}
		ObjectListing ol3= s3Client.listNextBatchOfObjects(ol2);
		System.out.println(ol2.getMarker());
		for (Bucket b : buckets) {
			
		    System.out.println("* " + b.getName());
		}
		AccessControlList ac1 = s3Client.getBucketAcl("image.pxsj.com");
		/*
		EC2CredentialsUtils ecd;
		AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard()
				.withRegion(Regions.CN_NORTH_1).build();*/
		try {
			
			String keyName = "3dcity/user/info/1509430826453JUf3zwJhp3.png";
			GetObjectRequest request2 = new GetObjectRequest(bucket, keyName);
			ObjectListing ol = s3Client.listObjects(bucket, "3dcity/");
			for (S3ObjectSummary so : ol.getObjectSummaries()) {
				System.out.println(so.getKey());
			}
		    S3Object o = s3Client.getObject(request2);
		    S3ObjectInputStream s3is = o.getObjectContent();
		    FileOutputStream fos = new FileOutputStream(new File("D:\\s3object\\"+ keyName));
		    byte[] read_buf = new byte[1024];
		    int read_len = 0;
		    while ((read_len = s3is.read(read_buf)) > 0) {
		        fos.write(read_buf, 0, read_len);
		    }
		    s3is.close();
		    fos.close();
		}
		    catch (AmazonServiceException e) {
		        System.err.println(e.getErrorMessage());
		        System.exit(1);
		    } catch (FileNotFoundException e) {
		        System.err.println(e.getMessage());
		        System.exit(1);
		    } catch (IOException e) {
		        System.err.println(e.getMessage());
		        System.exit(1);
		    }

	}

}
