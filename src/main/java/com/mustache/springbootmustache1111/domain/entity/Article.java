package com.mustache.springbootmustache1111.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "article3")
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 생성을 DB에 맡김
    private Long id;
    private String title;
    private String content;

    public Article(String title, String content){
        this.title = title;
        this.content = content;

    }
}
