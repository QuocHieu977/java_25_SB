package org.example.movieweb.repository;

import org.example.movieweb.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {

    Optional<Episode> findByMovieIdAndStatusAndDisplayOrder(Integer id, boolean status, Integer displayOrder);

    List<Episode> findByMovieIdOrderByDisplayOrderAsc(Integer id);
}
