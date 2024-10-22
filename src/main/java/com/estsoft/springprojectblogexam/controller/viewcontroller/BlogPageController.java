package com.estsoft.springprojectblogexam.controller.viewcontroller;

import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.entity.dto.ArticleViewResponseDTO;
import com.estsoft.springprojectblogexam.service.BlogService;
import com.estsoft.springprojectblogexam.user.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogPageController {

    @Autowired
    private BlogService service;

    @GetMapping("/articles")
    public String showArticles(Model model) {
        List<Article> articleList = service.findAll();

        List<ArticleViewResponseDTO> responseDTOs = articleList.stream()
                .map(ArticleViewResponseDTO::new)
                .toList();

        /*
        for (Article article : articleList) {
            ArticleViewResponseDTO dto = new ArticleViewResponseDTO(article);
            responseDTOs.add(dto);
        }
        */

        model.addAttribute("articles", responseDTOs);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String showArticle(Model model, @PathVariable Long id, @AuthenticationPrincipal Users users) {
        // @AuthenticationPrincipal로 Users객체를 받으면 Users객체에 대한 내용만 조회가 됨

        // 권한 및 인증에 대한 조회 아래와 같이 하면 권한부분까지 조회가 가능
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users principal = (Users) authentication.getPrincipal();

        Article article = service.findBy(id);

        ArticleViewResponseDTO responseDTO = new ArticleViewResponseDTO(article);

        model.addAttribute("article",responseDTO);

        return "showArticle";
    }

    // new-articles?id=1    -> 수정
    // new-articles         -> 생성
    // id 값은 필수값은 아니다. required = false (기본값은 true로 반드시 값이 있어야 된다.)
    @GetMapping("/new-article")
    public String addArticle(@RequestParam(required = false) Long id, Model model) {
        if(id == null) { // id가 없다? 새로운 게시글 생성
            model.addAttribute("article", new ArticleViewResponseDTO());  // 빈객체를 보내줌
        } else {        // 게시글 수정
            Article article = service.findBy(id);
            model.addAttribute("article",new ArticleViewResponseDTO(article));
        }

        return "newArticle";
    }
}
