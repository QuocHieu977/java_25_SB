package org.example.movieweb.service;

import org.example.movieweb.entity.Episode;
import org.example.movieweb.entity.Movie;
import org.example.movieweb.exception.BadRequestException;
import org.example.movieweb.exception.NotFoundException;
import org.example.movieweb.model.enums.MovieType;
import org.example.movieweb.model.request.CreateEpisodeRequest;
import org.example.movieweb.model.request.UpdateEpisodeRequest;
import org.example.movieweb.repository.EpisodeRepository;
import org.example.movieweb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class EpisodeService {
    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    MovieRepository movieRepository;

    public List<Episode> getEpisodesByMovieId(Integer id) {
        return episodeRepository.findByMovieIdOrderByDisplayOrderAsc(id);
    }

    public Episode getEpisodeByDisplayOrder(Integer id, String tap) {
        Integer coverTap = tap.equals("full") ? 1 : Integer.parseInt(tap);
        return episodeRepository.findByMovieIdAndStatusAndDisplayOrder(id, true, coverTap)
                .orElse(null);
    }

    public List<Episode> getAllEpisode(Integer id) {
        return episodeRepository.findByMovieIdOrderByDisplayOrderAsc(id);
    }

    public Episode createEpisode(CreateEpisodeRequest request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundException("Movie id not found"));

        List<Episode> episodes = episodeRepository.findByMovieId(request.getMovieId());

        if ((movie.getType() == MovieType.PHIM_LE || movie.getType() == MovieType.PHIM_CHIEU_RAP) && !episodes.isEmpty()) {
            throw new BadRequestException("This movie only produced 1 episode");
        }

        episodes.forEach(e -> {
            if (Objects.equals(e.getDisplayOrder(), request.getDisplayOrder())) {
                throw new BadRequestException("Episode with the same display order already exists.");
            }
        });

        Episode episode = Episode.builder()
                .name(request.getName())
                .duration(10)// druration defaults
                .displayOrder(request.getDisplayOrder())
                .videoUrl("https://www.youtube.com/embed/gCUg6Td5fgQ?si=OCtNkpFF03gq03ny")
                .status(request.getStatus())
                .movie(movie)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publishedAt(LocalDateTime.now())
                .build();
        return episodeRepository.save(episode);
    }

    public Episode updateEpisode(Integer id, UpdateEpisodeRequest request) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Episode id not found"));

        episode.setName(request.getName());
        episode.setDisplayOrder(request.getDisplayOrder());
        episode.setStatus(request.getStatus());
        return episodeRepository.save(episode);
    }

    public void deleteEpisode(Integer id) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Episode id not found"));
        episodeRepository.delete(episode);
    }
}
