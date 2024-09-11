package org.example.movieweb.service;

import org.example.movieweb.entity.Blog;
import org.example.movieweb.entity.Episode;
import org.example.movieweb.model.repository.BlogRepository;
import org.example.movieweb.model.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
