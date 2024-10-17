package com.estsoft.springprojectblogexam.controller;

import com.estsoft.springprojectblogexam.entity.dto.ArticleRequestDTO;
import com.estsoft.springprojectblogexam.entity.dto.ExternalAPIDTO;
import com.estsoft.springprojectblogexam.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;


@Slf4j
@Controller
public class ExternalApiController {

    @Autowired
    BlogService service;

    // RestTemplate을 사용해서 외부 API를 호출해서 데이터를 가져오기.
    @GetMapping("/api/external")
    public String callApi(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        // 문자열 형태로 가져옴. json으로 역직렬화 해야 됨
        /*
        ResponseEntity<String> json =  restTemplate.getForEntity(url, String.class);

        log.info("status code : " + json.getStatusCode());
        log.info(json.getBody());
         */

        // 리스트형태로 가져와볼까? 외부 API 호출, 역직렬화
        ResponseEntity<List<ExternalAPIDTO>> resultList =
                restTemplate.exchange(url, HttpMethod.GET,null, new ParameterizedTypeReference<>() {});


        for (ExternalAPIDTO externalAPIDTO : Objects.requireNonNull(resultList.getBody())) {
            ArticleRequestDTO requestDTO = new ArticleRequestDTO();
            requestDTO.setTitle(externalAPIDTO.getTitle());
            requestDTO.setContent(externalAPIDTO.getBody());
            service.saveArticle(requestDTO);
        }

        model.addAttribute("articles", service.findAll());
        return "articleList";
    }
}
