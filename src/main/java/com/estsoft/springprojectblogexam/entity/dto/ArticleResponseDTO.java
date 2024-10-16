package com.estsoft.springprojectblogexam.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDTO {
    private Long id;
    private String title;
    private String content;

    // 이외에 엔티티에 없는 다른 정보를 추가해서 response할 수 있다.
}
