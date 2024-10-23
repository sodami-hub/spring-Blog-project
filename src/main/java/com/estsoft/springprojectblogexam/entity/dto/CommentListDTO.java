package com.estsoft.springprojectblogexam.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentListDTO {
    private Long commentId;
    private Long articleId;
    private String body;
    private LocalDateTime createdAt;
}
