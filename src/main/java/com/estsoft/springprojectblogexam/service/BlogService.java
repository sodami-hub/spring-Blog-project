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

    private final BlogRepository blogRepository;

    BlogService(BlogRepository repo) {
        this.blogRepository = repo;
    }

    public Article saveArticle(ArticleRequestDTO request) {
        Article article = request.toEntity();
        return blogRepository.save(article);
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findBy(Long id) { // optional 값 받아오기 orElse, orElseGet, orElseThrow
        return blogRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("not found id :" + id));
    }

    public void deleteBy(Long id) {
        blogRepository.deleteById(id);
    }
    // 이 어노테이션때문에 entity의 값만 바꿨을 뿐인데 DB의 값이 변경 됨.
    @Transactional // 모든 과정이 성공하면 commit 중간에 문제가 발생(RuntimeException)하면 rollback
    public Article update(Long id, ArticleUpdateDTO updateDTO) {
        Article article = findBy(id); // 수정하고자 하는 row (Article 객체) 가져옴
        article.update(updateDTO.getTitle(),updateDTO.getContent());
        return article;
    }
}
