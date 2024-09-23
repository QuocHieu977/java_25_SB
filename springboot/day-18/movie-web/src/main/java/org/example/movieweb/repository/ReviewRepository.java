package org.example.movieweb.repository;

import org.example.movieweb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByMovieIdOrderByCreatedAtDesc(Integer id);
}
