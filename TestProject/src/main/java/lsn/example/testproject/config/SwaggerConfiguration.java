package lsn.example.testproject.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration

public class SwaggerConfiguration {

    //Docket이라는 객체를 리턴하는 함수
    @Bean
    public Docket api() {


        return new Docket(DocumentationType.SWAGGER_2)

                .apiInfo(apiInfo())  // 객체에 api정보를 담는다.
                .select()
                .apis(RequestHandlerSelectors.basePackage("lsn.example.testproject"))
                .paths(PathSelectors.any())
                .build();
    }

    //apiInfo에 담을 정보들을 담을 함수
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Lee Open API Test with Swagger")
                .description("설명부분")
                .version("1.1.4")
                .build();
    }

}
