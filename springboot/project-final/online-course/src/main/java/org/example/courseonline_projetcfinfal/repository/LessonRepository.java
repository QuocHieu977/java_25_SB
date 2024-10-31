package org.example.courseonline_projetcfinfal.repository;

import org.example.courseonline_projetcfinfal.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

}
