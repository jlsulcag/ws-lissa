package com.sulcacorp.lissa;

import org.springframework.context.annotation.Configuration;
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

	public Docket api() {
		/*
		 * return new Docket(DocumentationType.SWAGGER_2);
		 */
		
		final Contact DEFAULT_CONTACT = new Contact("SulcaCorp", "www.sulcacorp.com.pe", "jlsulcag@sulcacorp.com.pe");
		
		final ApiInfo DEFAULT_API_INFO = new ApiInfo("Lissa", "Sistema de Gestion de Clinicas", "2.0", "", "", "FREE", "");

		// Acotamos los controller al paquete del proyecto
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.sulcacorp.lissa")).paths(PathSelectors.any()).build();

	}

}
