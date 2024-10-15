package com.estsoft.springprojectblogexam.service;

import com.estsoft.springprojectblogexam.entity.AddArticleRequest;
import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.repository.BlogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BlogService {

    BlogRepository blogRepository;

    BlogService(BlogRepository repo) {
        this.blogRepository = repo;
    }

    //blog 게시글 저장
    //blogRepository.save(article)
    public Article saveArticle(AddArticleRequest request) {
        Article article = request.toEntity();

        return blogRepository.save(article);
    }

}
