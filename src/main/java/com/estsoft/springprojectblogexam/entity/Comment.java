package com.estsoft.springprojectblogexam.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


// Article Entity와 ManyToOne 연관 관계 맺기.

@Entity
public class Comment {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Article article;

    @Column
    private String body;

    @CreatedDate
    @Column(name ="created_at")
    private LocalDateTime createdAt;

}
