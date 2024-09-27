package org.example.movieweb.service;

import org.example.movieweb.entity.Blog;
import org.example.movieweb.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public Page<Blog> getBlogs(int page, int pageSize, Boolean status) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return blogRepository.findByStatus(status, pageable);
    }

    public Blog getBlogDetails(Integer id, String slug) {
        return blogRepository.findByIdAndSlugAndStatus(id, slug, true)
                .orElse(null);
    }

    public List<Blog> getBlogByStatus(Boolean status) {
        return blogRepository.findTop4ByStatusOrderByCreatedAtDesc(status);
    }
}
