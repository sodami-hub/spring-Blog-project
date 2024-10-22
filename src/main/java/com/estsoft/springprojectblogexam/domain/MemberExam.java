package com.estsoft.springprojectblogexam.domain;

// 객체의 연관관계 맵핑에 대한 내용 정리
// N(MemberExam):1(TeamExam) 관계 설정하기 -> 실무에서 가장 많이 사용되는 관계
// N쪽이 그 관계의 주인이다.

import jakarta.persistence.*;

@Entity
public class MemberExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "MEMBER_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")   // memberexam 테이블에 team_id FK 설정
    private TeamExam team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID") // lockerexam 테이블의 locker_id 와 fk 설정 (1:1 관계) - 연관관계의 주체
    private LockerExam locker;
}
