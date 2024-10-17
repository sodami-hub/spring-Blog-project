package com.estsoft.springprojectblogexam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing  // @CreateData, @LastModifiedDate 이런 기능을 사용하기 위한 어노테이션
@SpringBootApplication
public class SpringProjectBlogExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringProjectBlogExamApplication.class, args);
    }

}
