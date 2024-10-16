package com.estsoft.springprojectblogexam.entity.dto;

import com.estsoft.springprojectblogexam.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 클라이언트가 저장한 Article을 저장해서 서버로 전달하는 용도로 사용할 객체
// -> ArticleDTO
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequestDTO {
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
