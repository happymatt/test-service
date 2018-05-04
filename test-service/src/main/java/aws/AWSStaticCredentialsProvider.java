package aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

public class AWSStaticCredentialsProvider implements AWSCredentialsProvider {
	
	private AWSCredentials credentials;
	
	public AWSStaticCredentialsProvider() {}
	
	public AWSStaticCredentialsProvider(BasicAWSCredentials bac) {
		this.credentials = bac;
	}
	
	@Override
	public AWSCredentials getCredentials() {
		return this.credentials;
	}
	
	public void setCredentials(AWSCredentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public void refresh() {
		
	}

}
