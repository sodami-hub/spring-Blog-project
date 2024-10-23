package com.estsoft.springprojectblogexam.entity.dto;

import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentRequestDTO {
    private Article article;
    private String body;

    public Comment toEntity() {
        return new Comment(article,body);
    }
}
