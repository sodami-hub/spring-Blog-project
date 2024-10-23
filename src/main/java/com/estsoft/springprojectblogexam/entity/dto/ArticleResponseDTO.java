package com.estsoft.springprojectblogexam.entity.dto;

import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "블로그 조회 결과")  // swagger의 스키마에 적용되는 description
public class ArticleResponseDTO {
    @Schema(description = "게시글 ID", type = "Long") // swagger에 적용됨
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleResponseDTO(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public ArticleResponseDTO(Article article) {
        this.id = article.getId();
        this.title= article.getTitle();
        this.content =article.getContent();
        this.createdAt=article.getCreatedAt();
        this.updatedAt=article.getUpdatedAt();
    }
}
