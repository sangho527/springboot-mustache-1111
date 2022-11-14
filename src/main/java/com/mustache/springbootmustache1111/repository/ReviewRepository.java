package com.mustache.springbootmustache1111.repository;

import com.mustache.springbootmustache1111.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHospitalId(Integer hospitalId);
}