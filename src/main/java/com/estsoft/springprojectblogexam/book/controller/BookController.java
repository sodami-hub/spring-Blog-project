package com.estsoft.springprojectblogexam.book.controller;

import com.estsoft.springprojectblogexam.book.entity.Book;
import com.estsoft.springprojectblogexam.book.entity.BookDTO;
import com.estsoft.springprojectblogexam.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BookController {

    BookService service;

    BookController(BookService bookService) {
        this.service = bookService;
    }


    @PostMapping
    public String saveBook(@RequestParam Map<String,String> params, Model model) {
                                // @RequestParam String id, @RequestParam String name, @RequestParam String author ... 이렇게도 가능
        BookDTO dto = new BookDTO(params.get("id"),params.get("name"),params.get("author"));
        service.saveBook(dto);
        return "redirect:/books";  // Get /books  - status = 3xx (다른 ㅇu
    }

    @GetMapping
    public String showAll(Model model) {
        List<BookDTO> bookList = service.findAll().stream()
                .map(Book::convert)
                .toList();

        model.addAttribute("bookList",bookList);
        return "book/bookManagement";
    }


    @GetMapping("/{id}")
    public String findBy(@PathVariable String id, Model model) {
        Book book = service.findById(id);

        model.addAttribute("book",book.convert());
        return "book/bookDetail";
    }

/* GlobalExceptionHandler로 통합
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
*/

}
