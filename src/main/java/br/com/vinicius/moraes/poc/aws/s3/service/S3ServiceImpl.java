package br.com.vinicius.moraes.poc.aws.s3.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import br.com.vinicius.moraes.poc.aws.s3.util.Converter;

@Service
public class S3ServiceImpl implements S3Service {

	@Autowired
	private AmazonS3 s3client;

	@Value("${app.awsServices.bucketName}")
	private String bucketName;

	@Override
	public void uploadFile(MultipartFile file) {
		try {
			s3client.putObject(bucketName, file.getOriginalFilename(), Converter.convertMultiPartToFile(file));
		} catch (SdkClientException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public File downloadFile(String fileName) {
		try {
			S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, fileName));
			File file = new File("C:\\Users\\vinicius.moraes\\Downloads\\" + fileName);
			FileUtils.copyInputStreamToFile(s3object.getObjectContent(), file);
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
