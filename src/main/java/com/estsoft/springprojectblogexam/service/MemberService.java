package com.estsoft.springprojectblogexam.service;


import com.estsoft.springprojectblogexam.entity.Member;
import com.estsoft.springprojectblogexam.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor  // final 필드에 대한 자동 생성자 생성
public class MemberService {
    private final MemberRepository repository;

    // 생성자 주입을 롬복으로 처리가능
    public MemberService(MemberRepository repo) {
        this.repository=repo;
    }

    public List<Member> getAllMembers() {
        // select * from member
        return repository.findAll();  // Member 테이블의 모든 정보 조회.
    }

    public Member saveMember(Member member) {
        return repository.save(member);
    }
}
