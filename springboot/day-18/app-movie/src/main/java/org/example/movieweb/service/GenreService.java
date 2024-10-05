package org.example.movieweb.service;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.Genre;
import org.example.movieweb.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }
}
