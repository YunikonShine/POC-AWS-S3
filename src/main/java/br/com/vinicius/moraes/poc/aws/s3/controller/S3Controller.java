package br.com.vinicius.moraes.poc.aws.s3.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.vinicius.moraes.poc.aws.s3.service.S3Service;
import br.com.vinicius.moraes.poc.aws.s3.util.DownloadFile;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class S3Controller {

	@Autowired
	S3Service s3Service;

	@PostMapping("/upload")
	@ApiOperation(value = "Upload File")
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal error") })
	public ResponseEntity<?> upload(
			@RequestParam(name = "File to upload", required = true) @NotNull(message = "File is required") MultipartFile file)
			throws IOException {

		s3Service.uploadFile(file);

		return ResponseEntity.ok().build();

	}

	@PostMapping("/download")
	@ApiOperation(value = "Download File")
	@ApiResponses({ @ApiResponse(code = 200, message = "Ok"), @ApiResponse(code = 500, message = "Internal error") })
	public ResponseEntity<?> download(
			@RequestParam(name = "Image name to download", required = true) @NotNull(message = "File name is required") String fileName,
			HttpServletResponse response) throws IOException {

		DownloadFile.download(s3Service.downloadFile(fileName), response); 

		return ResponseEntity.ok().build();
		
	}

}
