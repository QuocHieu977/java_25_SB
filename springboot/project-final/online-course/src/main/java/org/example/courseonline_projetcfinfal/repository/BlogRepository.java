package org.example.courseonline_projetcfinfal.repository;

import org.example.courseonline_projetcfinfal.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

}
