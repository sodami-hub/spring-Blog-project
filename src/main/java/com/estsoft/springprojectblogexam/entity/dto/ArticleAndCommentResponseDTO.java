package com.estsoft.springprojectblogexam.entity.dto;

import com.estsoft.springprojectblogexam.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ArticleAndCommentResponseDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentListDTO> commentList;

    public ArticleAndCommentResponseDTO(Article article) {
        this.id = article.getId();
        this.title= article.getTitle();
        this.content =article.getContent();
        this.createdAt=article.getCreatedAt();
        this.updatedAt=article.getUpdatedAt();
    }
}
