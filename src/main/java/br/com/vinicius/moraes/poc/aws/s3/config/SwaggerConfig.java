package br.com.vinicius.moraes.poc.aws.s3.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.groupName("POC AWS S3")
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"POC AWS S3", 
				"POC for amazon web service S3 with Spring Boot", 
				"1.0.0", 
				"", 
				new Contact(
					"Vinícius De Moraes", 
					"", 
					"vinicius.moraes9@fatec.sp.gov.br"
				), 
				"",
				"", 
				Collections.emptyList());
	}

}
