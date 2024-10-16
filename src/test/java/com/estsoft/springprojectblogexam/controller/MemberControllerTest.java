package com.estsoft.springprojectblogexam.controller;

import com.estsoft.springprojectblogexam.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@AutoConfigureMockMvc // 클라이언트의 요청을 받아 줄 가짜MVC(MockMvc) 호출 및 구성
@SpringBootTest // test에 필요한 Bean 들을 가져다 붙여준다.
class MemberControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository repository;


    //Before(테스트 메소드 실행되기 전) 와 After (테스트 실행 이후) 시리즈가 있다.
    @BeforeEach // 각각의 테스트 케이스를 실행하기 전마다 실행
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getAllMembers() throws Exception { // 메소드 이름은 한글로도 가능하다.
        // given : 멤버 목록 저장하는 등의 기본 세팅
        // 서버가 시작할 때 data.sql이 실행되면서 기본적으로 멤버가 h2 db에 저장될 것임
        // given절 생략

        // when :  "/members" 요청이 들어감.(확인하고 싶은 메소드, 요청 등의 실행)
        ResultActions resultActions = mockMvc.perform(get("/members")
                .accept(MediaType.APPLICATION_JSON));

        // then : when절에서 실행한 결과에 대한 검증 => response에 대한 검증
        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));;
    }
}