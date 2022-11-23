package com.mustache.springbootmustache1111.domain.entity;

import com.mustache.springbootmustache1111.domain.dto.ArticleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // ArticleEntity를 Article Dto로 만들어주는 부분
    public static ArticleDto of(Article article) {
        return new ArticleDto(article.getId(),
                article.getTitle(), article.getContent());
    }
}
