package org.example.courseonline_projetcfinfal.repository;

import java.util.List;
import org.example.courseonline_projetcfinfal.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

  List<Review> findByCourseIdOrderByCreatedAtDesc(Integer id);
}
