package com.estsoft.springprojectblogexam.service;

import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.entity.Comment;
import com.estsoft.springprojectblogexam.entity.dto.ArticleResponseDTO;
import com.estsoft.springprojectblogexam.entity.dto.CommentListDTO;
import com.estsoft.springprojectblogexam.entity.dto.CommentRequestDTO;
import com.estsoft.springprojectblogexam.repository.BlogRepository;
import com.estsoft.springprojectblogexam.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    CommentService(CommentRepository repo, BlogRepository blogRepository) {
        this.commentRepository =repo;
        this.blogRepository= blogRepository;
    }

    public Comment saveComment(CommentRequestDTO dto) {
        return commentRepository.save(dto.toEntity());
    }

    public Comment findBy(Long id) {
        return commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("not found id. id:"+id));
    }

    public Comment updateBy(Long id, CommentRequestDTO dto) {
        Comment comment = findBy(id);
        comment.update(dto.getBody());
        return commentRepository.save(comment);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    public ArticleResponseDTO searchArticleAndComment(Long articleId) {
        Article article = blogRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("not found id/ id :"+articleId));
        List<Comment> commentList = commentRepository.findCommentsByArticleId(article.getId());

        List<CommentListDTO> commentListDTOS = commentList.stream().map(Comment::convertCommentListDTO).toList();

        ArticleResponseDTO responseDTO = new ArticleResponseDTO(article);
        responseDTO.setCommentList(commentListDTOS);
        return responseDTO;
    }
}
