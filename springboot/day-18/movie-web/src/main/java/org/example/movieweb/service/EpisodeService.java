package org.example.movieweb.service;

import org.example.movieweb.entity.Episode;
import org.example.movieweb.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {
    @Autowired
    EpisodeRepository episodeRepository;

    public List<Episode> getEpisodesByMovieId(Integer id) {
        return episodeRepository.findByMovieIdOrderByDisplayOrderAsc(id);
    }
}
