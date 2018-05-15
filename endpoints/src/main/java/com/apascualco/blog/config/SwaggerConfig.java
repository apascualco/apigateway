package com.apascualco.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Locale;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket vcApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfoBuilder())
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(Locale.class, String.class)
                .directModelSubstitute(OffsetDateTime.class, String.class)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build();
    }

    private ApiInfo apiInfoBuilder() {

        return new ApiInfoBuilder()
                .contact(new Contact("apascualco.com", "http://apascualco.com", "apascualco@gmail.com"))
                .description("API Gateway").license("")
                .termsOfServiceUrl("").title("API Gateway").version("V1")
                .build();
    }

}
