package com.bestseller.assignment.starbux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiDescriptionBuilder;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class BestsellerAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestsellerAssignmentApplication.class, args);
    }
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(generateApiInfo());
    }


    private ApiInfo generateApiInfo() {
        return new ApiInfoBuilder().title("BESTSELLER technical Test")
                .description("This application is developed to assess \"Mehdi Yeganehparast\"'s technical skills")
                .license("").licenseUrl("").version("1.0")
                .contact(
                        new Contact("Mehdi Yeganehparast",
                                "https://www.linkedin.com/in/mehdi-yeganehparast/",
                                "mehdi.yeganehparast@gmail.com"))
                .build();
    }
}
