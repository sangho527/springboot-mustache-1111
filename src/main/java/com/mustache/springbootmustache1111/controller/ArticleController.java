package com.mustache.springbootmustache1111.controller;


import com.mustache.springbootmustache1111.domain.dto.ArticleDto;
import com.mustache.springbootmustache1111.domain.dto.CommentDto;
import com.mustache.springbootmustache1111.domain.entity.Article;
import com.mustache.springbootmustache1111.domain.entity.Comment;
import com.mustache.springbootmustache1111.domain.entity.Hospital;
import com.mustache.springbootmustache1111.repository.ArticleRepository;
import com.mustache.springbootmustache1111.repository.CommentRepository;
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
    private final CommentRepository commentRepository;

    public ArticleController(ArticleRepository repository, CommentRepository commentRepository) {
        this.articleRepository = repository;
        this.commentRepository = commentRepository;
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
        if(!optArticle.isEmpty()){
            model.addAttribute("article", optArticle.get());

            List<Comment> comments = commentRepository.findByArticleId(id);
            model.addAttribute("comments", comments);
            return "articles/show";
        } else{
            return "articles/error";
        }
    }

    @PostMapping("/post")
    public String articles(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if (!optionalArticle.isEmpty()) {
            model.addAttribute("article", optionalArticle.get());
            return "articles/edit";
        } else {
            model.addAttribute("message", String.format("%d가 없습니다.", id));
            return "articles/error";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model) {
        log.info("title:{} content:{}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "redirect:/articles";
    }

    @PostMapping("/comment/post")
    public String comment(CommentDto commentDto) {
        Comment savedComment = commentRepository.save(commentDto.toEntity());
        return String.format("redirect:/articles/%d", savedComment.getArticleId());
    }

//    @GetMapping("/{id}")
//    public String show(@PathVariable Long id, Model model) {
//        Optional<Hospital> hospital = hospitalRepository.findById(id);
//        List<Comment> comments = commentRepository.findByArticleId(id);
//        log.info("comment cnt:{}", comments.size());
//        model.addAttribute("hospital", hospital.get());
//        model.addAttribute("comments", comments);
//        return "articles/show";
//    }

    @PostMapping("") // articles로 호출을 하면 인서트하는 부분
    public String createArticle(ArticleDto articleDto) { //Dto란 데이터 전송을 하는것
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }
}
