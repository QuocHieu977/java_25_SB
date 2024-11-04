package org.example.courseonline_projetcfinfal.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.example.courseonline_projetcfinfal.entity.Lesson;
import org.example.courseonline_projetcfinfal.entity.Section;
import org.example.courseonline_projetcfinfal.repository.LessonRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonService {

  private final LessonRepository lessonRepository;


  public Map<Integer, List<Lesson>> getLessonBySectionId(List<Section> sections) {
    Map<Integer, List<Lesson>> lessonsBySection = new HashMap<>();
    for (Section section : sections) {
      List<Lesson> lessons = lessonRepository.findLessonBySectionIdAndStatusOrderByDisplayOrderAsc(
          section.getId(), true);
      lessonsBySection.put(section.getId(), lessons);
    }
    return lessonsBySection;
  }
}
