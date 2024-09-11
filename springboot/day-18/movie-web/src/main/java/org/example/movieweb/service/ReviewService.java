package org.example.movieweb.service;

import org.example.movieweb.entity.Review;
import org.example.movieweb.model.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> getReviewsByMovieId(Integer id) {
        return reviewRepository.findByMovieIdOrderByCreatedAtAsc(id);
    }
}
