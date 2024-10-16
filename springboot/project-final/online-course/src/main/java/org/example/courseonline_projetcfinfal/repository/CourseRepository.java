package org.example.courseonline_projetcfinfal.repository;

import org.example.courseonline_projetcfinfal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findTop6ByStatusOrderByRatingDesc(Boolean status);

    List<Course> findTop6ByStatusOrderByCreatedAtDesc(Boolean status);
}
