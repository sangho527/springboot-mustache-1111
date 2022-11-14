package com.mustache.springbootmustache1111.repository;

import com.mustache.springbootmustache1111.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}