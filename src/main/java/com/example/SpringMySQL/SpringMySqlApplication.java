package com.example.SpringMySQL;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMySqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMySqlApplication.class, args);
	}

	@Bean
	public OpenAPI RestCrudAPI(@Value("${application-version}") String appVersion) {
		return new OpenAPI()
				.info(new Info()
					.title("Sample Rest CRUD API")
					.version(appVersion)
					.description("This is a demo REST API with MySQL.")
					.termsOfService("http://swagger.io/terms/")
					.license(new License().name("Apache 2.0").url("http://springdoc.org"))
				);
	}
}
