package com.mustache.springbootmustache1111.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "product") // 생략 가능
    public class Product {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long Number;

        @Column(nullable = false) // Not null
        private  String name;

        @Column(nullable = false)
        private  Integer price;

        @Column(nullable = false)
        private  Integer stock;

        private LocalDateTime createdAT;
        private LocalDateTime updatedAT;


    }
