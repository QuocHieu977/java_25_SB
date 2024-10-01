package org.example.courseonline_projetcfinfal.repository;

import org.example.courseonline_projetcfinfal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
