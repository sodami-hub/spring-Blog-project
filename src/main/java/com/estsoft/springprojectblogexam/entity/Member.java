package com.estsoft.springprojectblogexam.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter  // ORM방식 object relational mapping // 객체와 데이터베이스의 맵핑
@Entity
public class Member {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // @GeneratedValue -> Auto Increment
    @Column(name = "id", updatable = false)
    private Long id;		// DB 테이블의 id와 컬럼 매칭

    @Column(name = "name", nullable = false)
    private String name;	// DB 테이블의 name과 컬럼 매칭
}
