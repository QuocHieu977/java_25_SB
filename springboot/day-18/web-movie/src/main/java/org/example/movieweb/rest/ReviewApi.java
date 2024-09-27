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
       try {
           Review review = reviewService.createReview(request);
           return ResponseEntity.ok(review);
       } catch (Exception e) {
           ErrorResponse errorResponse = ErrorResponse.builder()
                   .status(HttpStatus.BAD_REQUEST)
                   .message(e.getMessage())
                   .build();
           return ResponseEntity.badRequest().body(errorResponse);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReview(@RequestBody UpdateReviewRequest request,
                                          @PathVariable Integer id) {
        try {
            Review review = reviewService.updateReview(id, request);
            return ResponseEntity.ok(review);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer id) {
        try {
            reviewService.deleteReview(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
