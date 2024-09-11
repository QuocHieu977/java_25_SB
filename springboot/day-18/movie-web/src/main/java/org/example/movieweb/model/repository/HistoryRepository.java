package org.example.movieweb.model.repository;

import org.example.movieweb.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}
