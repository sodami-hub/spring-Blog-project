package com.estsoft.springprojectblogexam.repository;

import com.estsoft.springprojectblogexam.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {  // <pk 타입, 블러올 객체(테이블)>

}
