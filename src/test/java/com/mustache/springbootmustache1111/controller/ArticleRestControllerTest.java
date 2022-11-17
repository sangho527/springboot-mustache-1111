package com.mustache.springbootmustache1111.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.springbootmustache1111.domain.dto.ArticleAddRequest;
import com.mustache.springbootmustache1111.domain.dto.ArticleAddResponse;
import com.mustache.springbootmustache1111.domain.dto.ArticleDto;
import com.mustache.springbootmustache1111.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("한 개의 게시글을 잘 조회하는지")
    void findArticle() throws Exception {
        Long id = 1l;

        given(articleService.getArticleById(id))
                .willReturn(new ArticleDto(1l, "1번 제목", "1번 내용"));

        String url = String.format("/api/v1/articles/%d", id);
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

        verify(articleService).getArticleById(id);
    }

    @Test
    @DisplayName("게시글 등록이 잘 되는지")
    void addArticle() throws Exception {
        ArticleAddRequest dto = new ArticleAddRequest("제목입니다", "내용입니다");

        given(articleService.addArticle(any())).willReturn(new ArticleAddResponse(1l,"제목입니다", "내용입니다"));

        mockMvc.perform(post("/api/v1/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new ArticleAddRequest("제목입니다", "내용입니다")))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

    }
}