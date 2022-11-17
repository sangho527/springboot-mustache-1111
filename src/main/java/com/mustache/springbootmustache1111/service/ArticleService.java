package com.mustache.springbootmustache1111.service;

import com.mustache.springbootmustache1111.domain.dto.ArticleAddRequest;
import com.mustache.springbootmustache1111.domain.dto.ArticleAddResponse;
import com.mustache.springbootmustache1111.domain.dto.ArticleDto;
import com.mustache.springbootmustache1111.domain.entity.Article;
import com.mustache.springbootmustache1111.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleDto getArticleById(Long id) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        Article article = optionalArticle.get();
        ArticleDto articleDto = Article.of(article);
        return articleDto;
    }

    public ArticleAddResponse addArticle(ArticleAddRequest dto) {
        Article article = dto.toEntity();
        Article savedArticle = articleRepository.save(article);
        return new ArticleAddResponse(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent());
    }
}
