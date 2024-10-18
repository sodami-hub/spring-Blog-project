package com.estsoft.springprojectblogexam.book.service;

import com.estsoft.springprojectblogexam.book.entity.Book;
import com.estsoft.springprojectblogexam.book.entity.BookDTO;
import com.estsoft.springprojectblogexam.book.repository.BookRepository;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepository repository;

    BookService(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    public void saveBook(BookDTO dto) {
        Book book = new Book(dto);
        repository.save(book);
    }


    public List<Book> findAll() {
        return repository.findAll(Sort.by("id")); // 기본값 : id를 기준으로 오름차순
    // select * from book order by id
    }

    public Book findById(String id) {
        return repository.findById(id).orElseThrow(()->new IllegalArgumentException("not found id : "+ id));
    }
}
