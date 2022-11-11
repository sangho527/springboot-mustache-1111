package com.mustache.springbootmustache1111.repository;

import com.mustache.springbootmustache1111.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
