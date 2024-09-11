package org.example.movieweb.model.repository;

import org.example.movieweb.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
    List<Episode> findByMovieIdOrderByDisplayOrderAsc(Integer id);
}