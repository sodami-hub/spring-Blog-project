package com.estsoft.springprojectblogexam.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Blog API 문서") // API 제목
                .description("블로그 CRUD API 문서 입니다.") // API 설명
                .version("1.0.0"); // 프로젝트 배포할 때 최초 API 버전
    }
}
