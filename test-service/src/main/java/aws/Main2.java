package aws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.common.collect.Lists;

public class Main2 {

	private static String appId = "AKIAOLNGAHTCV5LCGEDA";
	private static String secretyKey = "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";
	
	private static String id2 = "AKIAOUEBVVE57UDKOHVQ";
	private static String key2 = "TOtoyT0TJ35rJ46uTHYZPRRyoUMbhM9zTeNQihtP";
	
	private static String url = "https://basicresandroid.s3.cn-north-1.amazonaws.com.cn";

	public static void main(String[] args) throws IOException {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(appId, secretyKey);
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.CN_NORTH_1)
                .withClientConfiguration(new ClientConfiguration().withProtocol(Protocol.HTTP))
                .build();
		String bucketName = "basicresios";
		ObjectListing ol2 = s3Client.listObjects(bucketName);
		System.out.println("总图片数量为：" + ol2.getObjectSummaries().size());
		for (S3ObjectSummary o : ol2.getObjectSummaries()) {
			System.out.println(o.getKey());
		}
		List<String> list = Lists.newArrayList();
		for (Bucket bucket : s3Client.listBuckets()) {
			ObjectListing ol = s3Client.listObjects(bucketName);
			System.out.println("总图片数量为：" + ol.getObjectSummaries().size());
			for (S3ObjectSummary o : ol.getObjectSummaries()) {
				System.out.println(o.getKey());
				if (o.getKey().indexOf("_") > 0 
						&& !o.getKey().contains("assetbundle")
						&& !o.getKey().endsWith(".gz")) {
					System.out.println(o.getKey());
					list.add(o.getKey());
				}
			}
		}
		
		System.out.println("压缩图片数量为:" + list.size());
		String keyName = "3dcity/user/refund/1509621490541tSZr2GHuPF.png";
		
		ScalingMode smMode;
		S3Object s3Object = s3Client.getObject(bucketName, keyName);
    	Thumbnails.of(s3Object.getObjectContent())
    	.size(207, 276)
        .outputQuality(0.8f)
        .toFile("src/main/resources/resized/result2.png");
    	
    	String filePath = "src/main/resources/resized/result2.png";
		ObjectMetadata metadata = new ObjectMetadata();
		InputStream is = new FileInputStream(filePath);
		
		metadata.setContentLength(is.available());
		String scaledKey = "3dcity/user/refund/1509621490541tSZr2GHuPF_207X276.png";
		PutObjectRequest request = new PutObjectRequest(bucketName, scaledKey, is, metadata)
			.withCannedAcl(CannedAccessControlList.PublicRead);
		PutObjectResult result = s3Client.putObject(request);
		System.out.println(result.getETag());
		boolean dd = new File(filePath).delete();
		System.out.println(dd);
		
	/*	List<Bucket> buckets = s3.listBuckets();
		System.out.println("Your Amazon S3 buckets:");
		for (Bucket b : buckets) {
		    System.out.println("* " + b.getName());
		}
		
		String key = "test/aobama2.jpg";
		//ObjectListing ol = s3Client.listObjects("basicresandroid");
		ObjectListing ol = s3.listObjects(bucketName);
		List<S3ObjectSummary> objects = ol.getObjectSummaries();
		for (S3ObjectSummary os: objects) {
			System.out.println("* " + os.getKey());
		}
		
		try {
			
	    //上传
	    String filePath = "F:\\aobama.jpg";
		File file = new File(filePath);
		ObjectMetadata metadata = new ObjectMetadata();
		InputStream is = new FileInputStream(file);
		//metadata.setContentType();
		metadata.setContentLength(is.available());
		PutObjectRequest request = new PutObjectRequest(bucketName, key, is, metadata);
		PutObjectResult result = s3.putObject(request);
		System.out.println(result.getETag());*/
		
		try {
		//下载
			String bucketName2 = "basicresios";
		    String key = "/scene/0280/011_14_0_android.assetbundle";
		    S3Object o = s3Client.getObject(bucketName2, key);
		    S3ObjectInputStream s3is = o.getObjectContent();
		    FileOutputStream fos = new FileOutputStream(new File("F:\\" + key));
		    byte[] read_buf = new byte[1024];
		    int read_len = 0;
		    while ((read_len = s3is.read(read_buf)) > 0) {
		        fos.write(read_buf, 0, read_len);
		    }
		    s3is.close();
		    fos.close();
		} catch (AmazonServiceException e) {
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
