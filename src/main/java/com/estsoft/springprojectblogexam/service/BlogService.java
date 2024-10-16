package com.estsoft.springprojectblogexam.service;

import com.estsoft.springprojectblogexam.entity.dto.ArticleRequestDTO;
import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.entity.dto.ArticleUpdateDTO;
import com.estsoft.springprojectblogexam.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {

    BlogRepository blogRepository;

    BlogService(BlogRepository repo) {
        this.blogRepository = repo;
    }

    //blog 게시글 저장
    //blogRepository.save(article)
    public Article saveArticle(ArticleRequestDTO request) {
        Article article = request.toEntity();

        return blogRepository.save(article);
    }

    //blog 게시글 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 게시글 단건 조회
    public Article findBy(Long id) { // optional 값 받아오기 orElse, orElseGet, orElseThrow
        return blogRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("not found id :" + id));
    }

    // 게시글 단건 삭제
    public void deleteBy(Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional // 모든 과정이 성공하면 commit 중간에 문제가 발생(RuntimeException)하면 rollback
    public Article update(Long id, ArticleUpdateDTO updateDTO) {
        Article article = findBy(id); // 수정하고자 하는 row (Article 객체) 가져옴
        String title;
        String content;

        if(updateDTO.getTitle()==null || updateDTO.getTitle().isEmpty()) {
            title = article.getTitle();
        } else {
            title = updateDTO.getTitle();
        }
        if(updateDTO.getContent()==null || updateDTO.getContent().isEmpty()) {
            content = article.getContent();
        }else {
            content = updateDTO.getContent();
        }

        article.update(title,content);

        return article;
    }
}
