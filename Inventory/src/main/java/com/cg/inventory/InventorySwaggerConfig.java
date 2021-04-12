package com.cg.inventory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class InventorySwaggerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metadata()).select()
				.paths(PathSelectors.regex("/api/v1/.*")).build();
	}

	@SuppressWarnings("deprecation")
	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("Inventory Management").description("Inventory Management").contact("FSD_22")
				.version("1.0").build();
	}
}
