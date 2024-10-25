package org.example.courseonline_projetcfinfal.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.courseonline_projetcfinfal.entity.Review;
import org.example.courseonline_projetcfinfal.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;

  public List<Review> getReviewsByCourseId(Integer id) {
    return reviewRepository.findByCourseIdOrderByCreatedAtDesc(id);
  }
}
