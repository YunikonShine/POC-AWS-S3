package br.com.vinicius.moraes.poc.aws.s3.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.http.HttpServletResponse;

public class DownloadFile {

	public static void download(File temp, HttpServletResponse response) throws IOException {

		Path file = temp.toPath();
		if (Files.exists(file)) {
			response.addHeader("Content-Disposition", "attachment; filename=" + temp.getName());
			Files.copy(file, response.getOutputStream());
			response.getOutputStream().flush();
		} else {
			throw new FileNotFoundException();
		}

	}

}
