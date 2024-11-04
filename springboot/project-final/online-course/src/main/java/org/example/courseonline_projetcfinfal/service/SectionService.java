package org.example.courseonline_projetcfinfal.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.courseonline_projetcfinfal.entity.Section;
import org.example.courseonline_projetcfinfal.repository.SectionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SectionService {

  private final SectionRepository sectionRepository;

  public List<Section> getSectionByCourseId(Integer id) {
    return sectionRepository.findByCourseIdAndStatusOrderByDisplayOrderDesc(id, true);
  }
}
