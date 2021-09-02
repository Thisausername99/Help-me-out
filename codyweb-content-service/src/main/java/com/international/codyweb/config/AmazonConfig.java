package com.international.codyweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfig {
//	@Autowired
//	private VaultTemplate vaultTemplate;
	
	@Value("${aws.secretid}")
	private String accessKey;
	
	@Value("${aws.secretkey}")
	private String secretKey;
	
	
	@Bean
	public AmazonS3 s3() {
		AWSCredentials awsCredentials =
				new BasicAWSCredentials(accessKey, secretKey);
		return AmazonS3ClientBuilder
				.standard()
				.withRegion("us-east-2")
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.build();

	}
}
