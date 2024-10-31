package org.example.courseonline_projetcfinfal.repository;

import org.example.courseonline_projetcfinfal.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
