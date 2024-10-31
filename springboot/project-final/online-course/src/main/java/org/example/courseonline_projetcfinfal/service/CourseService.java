package org.example.courseonline_projetcfinfal.service;

import lombok.RequiredArgsConstructor;
import org.example.courseonline_projetcfinfal.entity.Course;
import org.example.courseonline_projetcfinfal.entity.Section;
import org.example.courseonline_projetcfinfal.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

  private final CourseRepository courseRepository;

  public List<Course> getCourseByRating(Boolean status) {
    return courseRepository.findTop6ByStatusOrderByRatingDesc(status);
  }

  public List<Course> getCourseNew(Boolean status) {
    return courseRepository.findTop6ByStatusOrderByCreatedAtDesc(status);
  }

  public Course getCourseDetails(Integer id, String slug) {
    return courseRepository.findByIdAndSlugAndStatus(id, slug, true)
        .orElse(null);
  }
}
