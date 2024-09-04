package org.example.movieweb.service;

import org.example.movieweb.entity.Blog;
import org.example.movieweb.model.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    public List<Blog> getBlogByStatus(Boolean status) {
        return blogRepository.findTop4ByStatusOrderByCreatedAtDesc(status);
    }
}
