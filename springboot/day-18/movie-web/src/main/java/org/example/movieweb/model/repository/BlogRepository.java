package org.example.movieweb.model.repository;

import org.example.movieweb.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findTop4ByStatusOrderByCreatedAtDesc(Boolean status);

    Page<Blog> findByStatus(Boolean status, Pageable pageable);

    //optional là trạng thái có hoặc không
    Optional<Blog> findByIdAndSlugAndStatus(Integer id, String slug, Boolean status);
}
