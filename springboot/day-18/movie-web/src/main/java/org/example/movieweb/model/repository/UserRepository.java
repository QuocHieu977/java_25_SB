package org.example.movieweb.model.repository;

import org.example.movieweb.entity.User;
import org.example.movieweb.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole role);
}
