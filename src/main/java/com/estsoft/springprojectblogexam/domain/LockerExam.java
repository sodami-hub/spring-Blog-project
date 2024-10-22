package com.estsoft.springprojectblogexam.domain;

import com.estsoft.springprojectblogexam.entity.Member;
import jakarta.persistence.*;

@Entity
public class LockerExam {
    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    @Column(name = "LOCKER_NAME")
    private String name;

    /* // 1:1 양방향 관계설정 주체가 되는 테이블의 컬럼을 mappedBy 한다.
    @OneToOne(mappedBy = "locker")
    private MemberExam member;
    */

}
