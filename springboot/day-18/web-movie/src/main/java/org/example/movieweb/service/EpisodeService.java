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

    public Episode getEpisodeByDisplayOrder(Integer id, String tap) {
        Integer coverTap = tap.equals("full") ? 1 : Integer.parseInt(tap);
        return episodeRepository.findByMovieIdAndStatusAndDisplayOrder(id, true, coverTap)
                .orElse(null);
    }
}
