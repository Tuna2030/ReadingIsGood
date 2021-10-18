package com.example.ReadingIsGood.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.example.ReadingIsGood.Controllers"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(
                        new ApiInfoBuilder().title("ReadingIsGood")
                                .description("ReadingIsGood is an online books retail firm which operates only on the Internet.")
                                .contact(new Contact("Tuna Rezaiazar", "http://www.tunarezai.com/", "tuna.rezaiazar@gmail.com"))
                                .version("1.0.0")
                                .build());
    }

}
