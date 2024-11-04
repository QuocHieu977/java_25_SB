package org.example.courseonline_projetcfinfal.repository;

import java.util.List;
import org.example.courseonline_projetcfinfal.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

  List<Lesson> findLessonBySectionIdAndStatusOrderByDisplayOrderAsc(Integer id, Boolean status);
}
