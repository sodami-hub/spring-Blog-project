package com.estsoft.springprojectblogexam.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class ExternalCommentDTO {
    private Long postId;
    private String body;
}
