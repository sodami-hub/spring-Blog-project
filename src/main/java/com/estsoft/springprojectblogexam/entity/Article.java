package com.estsoft.springprojectblogexam.entity;

import com.estsoft.springprojectblogexam.entity.dto.ArticleResponseDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)  // createdAt, updatedAt을 위한(변화감지) 감지하는 entity에 추가.
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id", updatable = false)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    // main 클래스에 @EnableJpaAuditing 이 활성화 필요!! row의 변경 및 생성을 Jpa가 감시 및 감지
    // row가 추가된 것을 감지하고 시간 값을 자동 할당. @EnableJpaAudi
    @CreatedDate
    @Column(name="created_at")
    private LocalDateTime createdAt;


    // row의 업데이트를 감지하고 그 값을 넣어줌
    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;


    @Transient
    private List<Comment> commentList;



    // 호출하는 매개변수에 따른 생성자 자동 생성
    @Builder
    public Article(String title, String content) {
        this.title=title;
        this.content =content;
    }

    public ArticleResponseDTO convert() {
        return new ArticleResponseDTO(this.id,this.title,this.content);
    }

    // update를 위한 메소드 setter 사용은 지양함
    public void update(String title, String content) {
        if(!title.isBlank()) this.title=title;
        if(!content.isBlank()) this.content=content;
    }


}
