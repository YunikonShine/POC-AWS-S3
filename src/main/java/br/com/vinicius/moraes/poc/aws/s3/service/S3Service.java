package br.com.vinicius.moraes.poc.aws.s3.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
	
	public void uploadFile(MultipartFile file);
	
	public File downloadFile(String fileName);

}
