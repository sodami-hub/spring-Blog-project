package com.estsoft.springprojectblogexam.entity;

import com.estsoft.springprojectblogexam.entity.dto.ArticleResponseDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    // 호출하는 매개변수에 따른 생성자 자동 생성
    @Builder
    public Article(String title, String content) {
        this.title=title;
        this.content =content;
    }

    public ArticleResponseDTO convert() {
        return new ArticleResponseDTO(this.getId(),this.getTitle(),this.getContent());
    }

    // update를 위한 메소드 setter 사용은 지양함
    public void update(String title, String content) {
        if(!title.isBlank()) this.title=title;
        if(!content.isBlank()) this.content=content;
    }


}
