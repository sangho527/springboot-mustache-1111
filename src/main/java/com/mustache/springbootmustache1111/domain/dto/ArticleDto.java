package com.mustache.springbootmustache1111.domain.dto;

import com.mustache.springbootmustache1111.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleDto {
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(title, content);
    }
}
