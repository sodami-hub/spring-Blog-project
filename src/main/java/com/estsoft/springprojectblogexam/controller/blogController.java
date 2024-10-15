package com.estsoft.springprojectblogexam.controller;

import com.estsoft.springprojectblogexam.entity.AddArticleRequest;
import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // logging 객체 생성(롬복)
@RestController
public class blogController {

    BlogService blogService;

    blogController(BlogService service) {
        this.blogService =service;
    }

    @PostMapping("/articles")
    public ResponseEntity<Article> saveArticle(@RequestBody AddArticleRequest request) {
        // @RequestBody AddArticleRequest request -> RequestBody의 josn 형태의 데이터가 전달받은 객체에 자동으로 파싱됨

        // 200번 반환.
        //return ResponseEntity.ok(blogService.saveArticle(request));

        log.warn("서버에서 반드시 확인해야 되는 정보는 warn으로...");
        log.info("{},{}, 개발 과정에서의 디버깅을위한 정보 배포시 반드시 삭제", request.getTitle(),request.getContent());

        Article article = blogService.saveArticle(request);

        // CREATED 는 201 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }
}
