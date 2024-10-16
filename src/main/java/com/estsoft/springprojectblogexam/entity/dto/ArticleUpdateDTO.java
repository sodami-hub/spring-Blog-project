package com.estsoft.springprojectblogexam.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleUpdateDTO {
    private String title;
    private String content;
}
