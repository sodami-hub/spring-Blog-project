package com.estsoft.springprojectblogexam.entity;

import com.estsoft.springprojectblogexam.entity.dto.CommentListDTO;
import com.estsoft.springprojectblogexam.entity.dto.CommentResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


// Article Entity와 ManyToOne 연관 관계 맺기.

@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String body;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm.ss")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    @Column(name ="created_at")
    private LocalDateTime createdAt;

    public Comment(Article article, String body) {
        this.article = article;
        this.body =body;
    }

    public CommentResponseDTO convertResponseDTO() {
        return new CommentResponseDTO(id, body,createdAt, article.getId());
    }

    public void update(String body) {
        this.body=body;
    }

    public CommentListDTO convertCommentListDTO() {
        return new CommentListDTO(id, article.getId(),body,createdAt);
    }

}
