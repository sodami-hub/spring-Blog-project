package com.estsoft.springprojectblogexam.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {
    @Test
    public void Test() {
        Article article = new Article("제목","내용");

        // 생성자의 매개변수가 많은 경우에 builder를 사용해서 다양한 생성자 사용가능
        Article articleBuilder = Article.builder()
                .title("제목")
                .content("내용")
                .build();
    }
}