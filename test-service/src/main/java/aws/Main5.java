package aws;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.common.collect.Lists;

public class Main5 {

	private static String appId = "AKIAOLNGAHTCV5LCGEDA";
	private static String secretyKey = "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";

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");
	
	private static List<String> list = Lists.newArrayList();
	private static String bucketName = "image.pxsj.com";
	private static AmazonS3 s3Client;
	public static void main(String[] args) throws IOException {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(appId,
				secretyKey);
		s3Client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(Regions.CN_NORTH_1)
				.withClientConfiguration(
						new ClientConfiguration().withProtocol(Protocol.HTTP))
				.build();
		
		ListObjectsRequest request = new ListObjectsRequest();
		request.setBucketName(bucketName);
		ObjectListing ol = s3Client.listObjects(request);
		addToList(ol);

		System.out.println("压缩图片数量为:" + list.size());

	}
	
	private static void addToList(ObjectListing ol) {
		for (S3ObjectSummary o : ol.getObjectSummaries()) {
			//boolean condition = o.getKey().indexOf("_") > 0;
			boolean condition = o.getKey().contains("clientLog");
			if (condition) {
				System.out.println(o.getKey() + "       "
						+ sdf.format(o.getLastModified()));
				list.add(o.getKey());
			}
		}
		if (ol.getObjectSummaries().size() > 0) {
			ListObjectsRequest request = new ListObjectsRequest();
			request.setBucketName(bucketName);
			ObjectListing ol2 = s3Client.listNextBatchOfObjects(ol);
			addToList(ol2);
		}
	}

}
