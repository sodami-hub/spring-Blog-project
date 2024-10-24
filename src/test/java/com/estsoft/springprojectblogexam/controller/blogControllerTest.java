package com.estsoft.springprojectblogexam.controller;

import com.estsoft.springprojectblogexam.entity.dto.ArticleRequestDTO;
import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.entity.dto.ArticleUpdateDTO;
import com.estsoft.springprojectblogexam.repository.BlogRepository;
import com.estsoft.springprojectblogexam.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
main 환경과 test 환경과의 DB 분리
test 환경의 repository 객체의 경우 mocking처리 -> 가상 객체로
 */


@SpringBootTest
@AutoConfigureMockMvc
class blogControllerTest {
    // 직렬화 jackson library 사용 -> 테스트 데이터 만들 때 필요.
    // object -> json(직렬화)

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    //@Mock // 목킹처리
    private MockMvc mockMvc;

    @Autowired
    private BlogRepository repository;

    @Autowired
    private BlogService blogService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        repository.deleteAll(); // test를 위한 분리된 db라면 먼저 test를 위해서 db에 있는 데이터 다 지우고 검증 실행
    }

    // Post "/articles API 테스트
    @Test
    public void addArticle() throws Exception {
        // given : article 저장 -
        ArticleRequestDTO article = new ArticleRequestDTO("제목","내용");

        // 직렬화 (object -> json)
        String json = objectMapper.writeValueAsString(article);

        // when : POST "/articles" API 호출
        ResultActions resultActions = mockMvc.perform(post("/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        // then : 호출 결과 검증
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(article.getTitle()))
                .andExpect(jsonPath("$.content").value(article.getContent()));

        List<Article> articleList = repository.findAll();
        Assertions.assertThat(articleList.size()).isEqualTo(1);
    }

    // 게시글 조회 API test
    @Test
    public void findAll() throws Exception{
        // given : 조회에 필요한 값 세팅
        Article article = repository.save(new Article("test title","test content"));

        // when : 조회 API
        ResultActions resultActions = mockMvc.perform(get("/articles")
                                            .accept(MediaType.APPLICATION_JSON));

        // then : 호출 결과 검증
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(article.getTitle()))
                .andExpect(jsonPath("$[0].content").value(article.getContent()));
    }

    @Test
    public void findBy() throws Exception {
        // given :
        Article article01 = repository.save(new Article("title01","content01"));
        Article article02 = repository.save(new Article("title02","content02"));
        Long id = article01.getId();

        // when
        ResultActions resultActions = mockMvc.perform(get("/articles/{id}", id) // id 값을 parsing 해줌
                        .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value(article01.getTitle()))
                .andExpect(jsonPath("$.content").value(article01.getContent()));
    }

    @Test
    public void deleteBy() throws Exception {
        // given :
        Article article01 = repository.save(new Article("title01", "content01"));
        Long id = article01.getId();

        // when :
        ResultActions resultActions01 = mockMvc.perform(delete("/articles/{id}", id));  // 삭제하고

        ResultActions resultActions02 = mockMvc.perform(get("/articles/{id}", id)
                .accept(MediaType.APPLICATION_JSON));   // 해당 데이터 불러옴


        // then :
        resultActions01.andExpect(status().isOk());  // 삭제 결과가 200번 인지

        resultActions02.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("")); // 실제로 제대로 삭제 됐는지

        List<Article> list = repository.findAll();
        Assertions.assertThat(list).isEmpty();  // 게시물이 정말 없는지
    }

    // 단건 조회 실패 케이스의 테스트 - 4xx 예외처리가 잘 됐는지 테스트 코드
    @Test
    public void findOneException() throws Exception {
        // given :

        // when
        ResultActions resultActions = mockMvc.perform(get("/articles/{id}", 1L) // id 값을 parsing 해줌
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isBadRequest());

        assertThrows(IllegalArgumentException.class
                , ()->blogService.findBy(1L));
    }

    @Test
    public void updateArticle() throws Exception {
        Article article = repository.save(new Article("blog title","blog content"));
        Long id = article.getId(); // 저장할 때 할당되는 id 가져오기

        // 수정 데이터 -> json
        ArticleRequestDTO request = new ArticleRequestDTO("변경 제목", "변경 내용");
        // 직렬화, 역직렬화는(readValue ...)
        String updateJson = objectMapper.writeValueAsString(request);

        ResultActions resultActions = mockMvc.perform(put("/articles/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("변경 제목"))
                .andExpect(jsonPath("$.content").value("변경 내용"));

        Article article01 = repository.findById(id).get();
        Assertions.assertThat(article01.getTitle()).isEqualTo("변경 제목");
    }

    // update 하려는 id가 존재하지 않을 때 4xx 검증
    @Test
    public void updateFailException() throws Exception {
        ArticleUpdateDTO requestBody = new ArticleUpdateDTO("change","change");
        String updateJson = objectMapper.writeValueAsString(requestBody);
        Long notExistId = 1000L;
        ResultActions resultActions = mockMvc.perform(put("/articles/{id}",notExistId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson));

        resultActions.andExpect(status().is4xxClientError()); // 수정하고자 하는 게시물의 id가 없으면 4xx

        assertThrows(IllegalArgumentException.class, ()->blogService.update(notExistId,requestBody));
    }
}