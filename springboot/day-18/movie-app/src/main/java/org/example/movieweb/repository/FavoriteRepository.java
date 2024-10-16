package org.example.movieweb.repository;

import org.example.movieweb.entity.Favorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorie, Integer> {
}
