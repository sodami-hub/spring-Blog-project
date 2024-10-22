package com.estsoft.springprojectblogexam.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class TeamExam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TEAM_ID")
    private Long id;

    @Column(name="TEAM_NAME")
    private String name;

    /*
    //양방향으로 설정... OneToMany 잘 안씀... 성능상 너무 느려짐 - 지연로딩을 함
    // TEAM을 하나 부르기 위해서 TEAM에 속한 member를 다 읽어야 됨....
    @OneToMany(mappedBy ="team") // 연관관계의 주인을 명시 -> 연관관계 N 쪽의 FK 를 가지고 있는 객체 이름을 적으면 됨
    private List<MemberExam> memberList;
    */

}
