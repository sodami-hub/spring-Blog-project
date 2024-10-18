package com.estsoft.springprojectblogexam.book.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    private String id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String author;


    public Book(BookDTO dto) {
        this.id= dto.getId();
        this.name=dto.getName();
        this.author=dto.getAuthor();
    }

    public BookDTO convert() {
        return new BookDTO(this.getId(), this.getName(), this.getAuthor());
    }
}
