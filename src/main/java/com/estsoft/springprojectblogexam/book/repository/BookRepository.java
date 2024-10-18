package com.estsoft.springprojectblogexam.book.repository;

import com.estsoft.springprojectblogexam.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
}
