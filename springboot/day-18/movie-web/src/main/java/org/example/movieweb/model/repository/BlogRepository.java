package org.example.movieweb.model.repository;

import org.example.movieweb.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findTop4ByStatusOrderByCreatedAtDesc(Boolean status);
}
