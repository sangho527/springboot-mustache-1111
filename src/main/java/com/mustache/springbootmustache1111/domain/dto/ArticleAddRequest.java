package com.mustache.springbootmustache1111.domain.dto;


import com.mustache.springbootmustache1111.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity() {
        Article article = Article.builder()
                .title(this.getTitle())
                .content(this.getContent())
                .build();
        return article;
    }
}
