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
public class ArticleController {
    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new") // 새로운 게시글을 작성하는 페이지
    public String createArticlePage() {
        return "articles/new";
    }

    @GetMapping("") // index, 게시판 메인화면
    public String index() {
        return "redirect:/articles/list";
    }

    @GetMapping("/list") // 게시판 페이지(findAll)
    public String listPage(Model model) {
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @PostMapping("") // 작성한 게시글을 DB에 저장(add)
    public String createArticle(ArticleDto articleDto) {
        log.info(articleDto.getTitle());
        Article savedArticle=articleRepository.save(articleDto.toEntity());
        return String.format("redirect:/articles/%d",savedArticle.getId()) ;
    }

    @GetMapping(value = "/{id}") // 하나의 게시글을 보여주는 페이지 (select)
    public String showSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);

        if(!optArticle.isEmpty()){
            model.addAttribute("article", optArticle.get());
            return "articles/show";
        } else {
            return "articles/error";
        }
    }

    @GetMapping(value = "/{id}/edit") // 게시글을 수정하는 페이지
    public String editPage(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if(!optionalArticle.isEmpty()){
            model.addAttribute("article", optionalArticle.get());
            return "articles/edit";
        } else {
            model.addAttribute("message", String.format("%d가 없습니다.", id));
            return "articles/error";
        }
    }
    @PostMapping(value = "/{id}/update") // 수정한 게시글을 다시 DB에 저장(update)
    public String updateArticle(@PathVariable Long id, ArticleDto articleDto, Model model) {
        log.info("title:{} content:{}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }

    @GetMapping(value = "/{id}/delete") // 게시글 삭제
    public String deleteArticle(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "redirect:/articles";
    }
}
