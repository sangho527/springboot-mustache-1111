package com.mustache.springbootmustache1111.domain.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product") //생략가능
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false) //not null
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer Stock;

    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
}
