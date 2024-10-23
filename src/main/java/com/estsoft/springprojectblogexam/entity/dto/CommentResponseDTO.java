package com.estsoft.springprojectblogexam.entity.dto;

import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private String body;
    private LocalDateTime createdAt;
    private Article article;
    private List<Comment> commentList;

    public CommentResponseDTO(Long id, String body, LocalDateTime time, Article article) {
        this.id=id;
        this.body=body;
        this.createdAt=time;
        this.article=article;
    }

    public CommentResponseDTO(Comment comment) {
        this.id = comment.getId();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();
        this.article = comment.getArticle();
    }
}
