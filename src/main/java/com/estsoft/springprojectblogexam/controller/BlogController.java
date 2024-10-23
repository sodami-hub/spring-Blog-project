package com.estsoft.springprojectblogexam.controller;

import com.estsoft.springprojectblogexam.entity.dto.ArticleRequestDTO;
import com.estsoft.springprojectblogexam.entity.Article;
import com.estsoft.springprojectblogexam.entity.dto.ArticleResponseDTO;
import com.estsoft.springprojectblogexam.entity.dto.ArticleUpdateDTO;
import com.estsoft.springprojectblogexam.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "블로그 게시글 저장/수정/삭제/조회 API", description ="게시글 관련 API") // swagger에 들어갈 이름
@Slf4j // logging 객체 생성(롬복)
@RestController
//@RequestMapping("/api")  프리픽스 앞에다 붙이는거 한번에 처리
public class BlogController {

    BlogService blogService;

    BlogController(BlogService service) {
        this.blogService = service;
    }

    @Operation(summary = "게시글 저장", description = "블로그에 새 게시글 저장하기")
    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponseDTO> saveArticle(@RequestBody ArticleRequestDTO request) {
        // @RequestBody AddArticleRequest request -> RequestBody의 josn 형태의 데이터가 전달받은 객체에 자동으로 파싱됨
        // db로 전달하는 값이 클라이언트에서 받는 값에서 추가될 수 있다. 그래서 DTO 사용 한다.


        // 200번 반환.
        //return ResponseEntity.ok(blogService.saveArticle(request));
/*
        log.warn("서버에서 반드시 확인해야 되는 정보는 warn으로...");
        log.info("{},{}, 개발 과정에서의 디버깅을위한 정보 배포시 반드시 삭제", request.getTitle(),request.getContent());
 */
        // RequsetDTO로 DB에 보내고, RequestDTO로 클라이언트에 전달하고.
        Article article = blogService.saveArticle(request);

        // CREATED 는 201 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(article.convert());
    }


    // Article(Entity) 와 Response 값이 같으리라는 보장이없다.
    // Response에 Entity정보 외에 다른 정보가 추가될 수도 있다. -> DTO의 존재 이유!!
    @Operation(summary = "블로그 전체 목록 보기", description = "블로그 메인 화면의 전체 게시물 목록")
    @ApiResponse(responseCode = "100", description = "요청 성공", content = @Content(mediaType = "application/json"))
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponseDTO>> findAll() {
        List<Article> articleList = blogService.findAll();

        // ResponseDTO로 변환 -> 필요시 추가 정보도 전달.
        List<ArticleResponseDTO> dtoList = new ArrayList<>();
        for (Article article : articleList) {
            dtoList.add(article.convert());
        }

        /*
        //stream 사용해서 위의 모든 코드를 한줄로.
        List<ArticleResponseDTO> listDTO = blogService.findAll().stream()
                .map(Article::convert)
                .toList();
        */

        return ResponseEntity.ok(dtoList);
    }

    // 게시글 단건 조회
    @Operation(summary = "게시물 단건 조회", description = "게시물 id를 사용해서 해당 게시물 상세보기")
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponseDTO> findById(@PathVariable Long id) {
        Article article = blogService.findBy(id);  // 잘못된 id 인 경우 : Exception(5xx Server error) -> 4xx

        return ResponseEntity.ok(article.convert());
    }

    // 게시글 단건 삭제
    // @RequestMapping(method = RequestMethod.DELETE, value = "api/article/{id}")  요것과 @DeleteMapping과 동일
    @Operation(summary = "게시물 삭제", description = "게시물의 id를 사용해서 해당 게시물 삭제")
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        blogService.deleteBy(id);
        return ResponseEntity.ok().build();
    }

    // 게시글 수정
    // /article/{id} 수정 API // 수정 값은 RequestBody
    @Operation(summary = "게시물 내용 수정", description = "해당 id의 게시물 수정")
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponseDTO> updateById(@RequestBody ArticleUpdateDTO UpdateRequest
            , @PathVariable Long id) {
        ArticleResponseDTO response = blogService.update(id, UpdateRequest).convert();
        // update용 DTO를 새로 만드는게 실무에서는 일반적. DTO를 세분화한다. Entity를 직접 사용하지 않음.
        return ResponseEntity.ok(response);
    }


    // ================================================== Exception Handler ==

    //(Illegal...) 괄호 안의 예외 발생시 실행되는 메소드
    // Exception이 많아지면 요것만 모아놓는 클래스를 따로 만든다.

    /* GlobalExceptionHandler로 통합
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); //
    }
*/
}
