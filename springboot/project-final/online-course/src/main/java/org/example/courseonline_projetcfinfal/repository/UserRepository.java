package org.example.courseonline_projetcfinfal.repository;

import org.example.courseonline_projetcfinfal.entity.User;
import org.example.courseonline_projetcfinfal.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  List<User> findByRole(UserRole userRole);
}
