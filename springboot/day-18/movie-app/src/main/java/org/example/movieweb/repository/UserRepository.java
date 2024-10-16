package org.example.movieweb.repository;

import org.example.movieweb.entity.User;
import org.example.movieweb.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByRole(UserRole role);

    Optional<User> findByEmail(String email);
}
