package org.example.movieweb.rest;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.Review;
import org.example.movieweb.model.request.CreateReviewRequest;
import org.example.movieweb.model.request.UpdateReviewRequest;
import org.example.movieweb.model.response.ErrorResponse;
import org.example.movieweb.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewApi {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody CreateReviewRequest request) {
        Review review = reviewService.createReview(request);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody UpdateReviewRequest request,
                                          @PathVariable Integer id) {
        Review review = reviewService.updateReview(id, request);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }
}
