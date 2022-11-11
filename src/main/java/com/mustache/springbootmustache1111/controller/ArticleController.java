package com.mustache.springbootmustache1111.controller;


import com.mustache.springbootmustache1111.domain.dto.ArticleDto;
import com.mustache.springbootmustache1111.domain.entity.Article;
import com.mustache.springbootmustache1111.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController { // 여기는 mustache등을 맵핑해주는 파일

    private final ArticleRepository articleRepository; // repository를 di

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    @GetMapping("")
    public String listPage(Model model){
        List<Article> articles = articleRepository.findAll(); // 이 페이지에 들어오면 전체 조회
        model.addAttribute("articles", articles); // 이 데이터를 list에 보내줘라
        return "articles/list";

    }@GetMapping("/new")
    public String createArticlePage(){
        return "articles/new";
    }

    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);

        if (!optArticle.isEmpty()) {
            // Optional.get() ---> Article
            model.addAttribute("article", optArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @PostMapping("") // articles로 호출을 하면 인서트하는 부분
    public String createArticle(ArticleDto articleDto) { //Dto란 데이터 전송을 하는것
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        return String.format("redirect:/articles/%d", savedArticle.getId());

    }
}
