package com.estsoft.springprojectblogexam.entity.dto;

import com.estsoft.springprojectblogexam.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// view에 필요한 필드들을 정의한 DTO
@Getter
@Setter
@NoArgsConstructor
public class ArticleViewResponseDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public ArticleViewResponseDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
    }
}
