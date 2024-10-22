package com.estsoft.springprojectblogexam.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "블로그 조회 결과")  // swagger의 스키마에 적용되는 description
public class ArticleResponseDTO {
    @Schema(description = "게시글 ID", type="Long")
    private Long id;

    @Schema(description = "게시글 제목", type="String")
    private String title;
    
    @Schema(description = "게시글 내용", type="String")
    private String content;

    // 이외에 엔티티에 없는 다른 정보를 추가해서 response할 수 있다.
}
