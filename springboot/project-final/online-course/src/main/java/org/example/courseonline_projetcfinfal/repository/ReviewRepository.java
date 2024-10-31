package org.example.courseonline_projetcfinfal.repository;

import java.util.List;
import org.example.courseonline_projetcfinfal.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

  List<Review> findByCourseIdOrderByCreatedAtDesc(Integer id);
}
