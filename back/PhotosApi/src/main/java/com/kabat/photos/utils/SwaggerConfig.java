package com.kabat.photos.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info()
							.title("Microservicio BackEnd API Photos")
							.version("1.0.0")
							.description("Documentación del Microservicio PhotosApi")
							.license(new License().name("Apache 2.0").url("http://springdoc.org")))
							.externalDocs(new ExternalDocumentation()
							.description("Referencias y URL para la ejecucion de los servicios PhotosApi")
							.url("https://swagger.io"));
	}
}