package com.estsoft.springprojectblogexam.controller;


import com.estsoft.springprojectblogexam.entity.Member;
import com.estsoft.springprojectblogexam.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService service) {
        this.memberService=service;
    }

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    // HTTP Psot Method 요청(/members)을 받으면 실행되는 컨트롤러 메소드
    // service로 연결됨.
    @PostMapping("/members")
    public Member saveMember(@RequestBody Member member) {
        // member 정보를 저장하는 서비스 코드 호출
        return memberService.saveMember(member);
    }
}
