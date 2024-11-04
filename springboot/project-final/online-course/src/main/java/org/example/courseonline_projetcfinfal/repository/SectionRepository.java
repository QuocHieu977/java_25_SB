package org.example.courseonline_projetcfinfal.repository;

import java.util.List;
import org.example.courseonline_projetcfinfal.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

  List<Section> findByCourseIdAndStatusOrderByDisplayOrderDesc(Integer id, Boolean status);
}
