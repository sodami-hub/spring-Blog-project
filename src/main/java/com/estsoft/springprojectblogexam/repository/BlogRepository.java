package com.estsoft.springprojectblogexam.repository;

import com.estsoft.springprojectblogexam.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Article,Long> {
}
