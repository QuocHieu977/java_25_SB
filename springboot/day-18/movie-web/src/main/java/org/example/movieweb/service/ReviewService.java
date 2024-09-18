package org.example.movieweb.service;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.Movie;
import org.example.movieweb.entity.Review;
import org.example.movieweb.entity.User;
import org.example.movieweb.model.request.CreateReviewRequest;
import org.example.movieweb.model.request.UpdateReviewRequest;
import org.example.movieweb.repository.MovieRepository;
import org.example.movieweb.repository.ReviewRepository;
import org.example.movieweb.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public List<Review> getReviewsByMovieId(Integer id) {
        return reviewRepository.findByMovieIdOrderByCreatedAtAsc(id);
    }

    public Review createReview(CreateReviewRequest request) {
        // TODO: Fix user, sau này user sẽ là user đang đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + request.getMovieId()));

        // TODO: validate rating(1->10), content
        if(!StringUtils.hasText(request.getContent())) {
            throw new RuntimeException("Content cannot be empty");
        }

        if(request.getRating() == null) {
            throw new RuntimeException("Rating cannot be empty");
        }

        if(request.getRating()<1 || request.getRating()>10 ) {
            throw new RuntimeException("Rating must be between 1 and 10");
        }

        Review review = Review.builder()
                .content(request.getContent())
                .rating(request.getRating())
                .movie(movie)
                .user(user)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return reviewRepository.save(review);
    }

    public Review updateReview(Integer id, UpdateReviewRequest request) {
        // TODO: Fix user, sau này user sẽ là user đang đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id"));

        // check user is owner of review
        if ((!review.getUser().getId().equals(user.getId()))) {
            throw new RuntimeException("You are not owner of this review");
        }

        // TODO: validate rating(1->10), content
        if(!StringUtils.hasText(request.getContent())) {
            throw new RuntimeException("Content cannot be empty");
        }

        if(request.getRating() == null) {
            throw new RuntimeException("Rating cannot be empty");
        }

        if(request.getRating()<1 || request.getRating()>10 ) {
            throw new RuntimeException("Rating must be between 1 and 10");
        }

        review.setContent(request.getContent());
        review.setRating(request.getRating());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        // TODO: Fix user, sau này user sẽ là user đang đăng nhập
        Integer userId = 1;
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id"));

        // check user is owner of review
        if ((!review.getUser().getId().equals(user.getId()))) {
            throw new RuntimeException("You are not owner of this review");
        }

        reviewRepository.delete(review);
    }
}
