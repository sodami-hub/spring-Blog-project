package com.estsoft.springprojectblogexam.controller;

import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.entity.Comment;
import com.estsoft.springprojectblogexam.entity.dto.ArticleAndCommentResponseDTO;
import com.estsoft.springprojectblogexam.entity.dto.CommentRequestDTO;
import com.estsoft.springprojectblogexam.entity.dto.CommentResponseDTO;
import com.estsoft.springprojectblogexam.service.BlogService;
import com.estsoft.springprojectblogexam.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment 저장/수정/삭제/조회 API", description ="Comment API")
@RestController
public class CommentController {

    private final CommentService commentService;
    private final BlogService blogService;

    CommentController(CommentService service, BlogService blogService) {
        this.commentService=service;
        this.blogService=blogService;
    }

    @Operation(summary = "Comment 저장", description = "게시물에 comment 달기")
    @PostMapping("/api/articles/{articleId}/comment")
    public ResponseEntity<CommentResponseDTO> saveComment(@PathVariable Long articleId, @RequestBody CommentRequestDTO dto) {
        Article article = blogService.findBy(articleId);
        dto.setArticle(article);
        Comment comment = commentService.saveComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment.convertResponseDTO());
    }

    @Operation(summary = "Comment의 id로 comment 조회", description = "id로 comment 조회")
    @GetMapping("/api/articles/comment/{commentId}")
    public ResponseEntity<CommentResponseDTO> findById(@PathVariable Long commentId) {
        Comment comment = commentService.findBy(commentId);
        return ResponseEntity.ok().body(new CommentResponseDTO(comment));
    }

    @Operation(summary = "Comment의 id로 comment 수정", description = "id로 comment 수정")
    @PutMapping("/api/articles/comment/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateById(@PathVariable Long commentId, @RequestBody CommentRequestDTO dto) {
        Comment comment = commentService.updateBy(commentId,dto);
        return ResponseEntity.ok(comment.convertResponseDTO());
    }

    @Operation(summary = "Comment의 id로 comment 삭제", description = "id로 comment 삭제")
    @DeleteMapping("/api/articles/comment/{commentId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long commentId) {
        commentService.deleteById(commentId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "게시글의 id로 게시글 + 코멘트 조회", description = "게시글 + 코멘트")
    @GetMapping("/api/articles/{articleId}/comment")
    public ResponseEntity<ArticleAndCommentResponseDTO> searchAll(@PathVariable Long articleId) {
        ArticleAndCommentResponseDTO dto = commentService.searchArticleAndComment(articleId);
        return ResponseEntity.ok().body(dto);
    }

}
