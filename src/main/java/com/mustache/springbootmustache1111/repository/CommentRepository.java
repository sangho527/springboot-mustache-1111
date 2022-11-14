package com.mustache.springbootmustache1111.repository;

import com.mustache.springbootmustache1111.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long id);
}