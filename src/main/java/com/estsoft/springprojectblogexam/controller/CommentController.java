package com.estsoft.springprojectblogexam.controller;

import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.entity.Comment;
import com.estsoft.springprojectblogexam.entity.dto.ArticleResponseDTO;
import com.estsoft.springprojectblogexam.entity.dto.CommentRequestDTO;
import com.estsoft.springprojectblogexam.entity.dto.CommentResponseDTO;
import com.estsoft.springprojectblogexam.service.BlogService;
import com.estsoft.springprojectblogexam.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    private final CommentService commentService;
    private final BlogService blogService;

    CommentController(CommentService service, BlogService blogService) {
        this.commentService=service;
        this.blogService=blogService;
    }

    @PostMapping("/api/articles/{articleId}/comment")
    public ResponseEntity<CommentResponseDTO> saveComment(@PathVariable Long articleId, @RequestBody CommentRequestDTO dto) {
        Article article = blogService.findBy(articleId);
        dto.setArticle(article);
        Comment comment = commentService.saveComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment.convertResponseDTO());
    }

    @GetMapping("/api/articles/comment/{commentId}")
    public ResponseEntity<CommentResponseDTO> findById(@PathVariable Long commentId) {
        Comment comment = commentService.findBy(commentId);
        return ResponseEntity.ok().body(new CommentResponseDTO(comment));
    }

    @PutMapping("/api/articles/comment/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateById(@PathVariable Long commentId, @RequestBody CommentRequestDTO dto) {
        Comment comment = commentService.updateBy(commentId,dto);
        return ResponseEntity.ok(comment.convertResponseDTO());
    }

    @DeleteMapping("/api/articles/comment/{commentId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long commentId) {
        commentService.deleteById(commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/articles/{articleId}/comment")
    public ResponseEntity<ArticleResponseDTO> searchAll(@PathVariable Long articleId) {
        ArticleResponseDTO dto = commentService.searchArticleAndComment(articleId);
        return ResponseEntity.ok().body(dto);
    }

}
