package org.example.movieweb.service;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.Movie;
import org.example.movieweb.model.enums.MovieType;
import org.example.movieweb.repository.MovieRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Page<Movie> getMoviesByType(MovieType movieType, Boolean status, int page, int pageSize) {
        // phân trang và sắp xếp thời gian tạo giảm dần -> mới nhất sẽ lên đầu
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending());
        return movieRepository.findByTypeAndStatus(movieType, status, pageable);
    }

    public Movie getMovieDetails(Integer id, String slug) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, true)
                .orElse(null);
    }

    public List<Movie> getMovieRecommendations(Integer id, MovieType type) {
        return movieRepository.findByTypeAndStatus(type, true).stream()
                .filter(movie -> !movie.getId().equals(id))
                .sorted((o1, o2) -> (int) (o2.getRating() - o1.getRating()))
                .limit(6)
                .toList();
    }

    public List<Movie> getMovieSortByRating(Boolean status) {
        return movieRepository.findTop4ByStatusOrderByRatingDesc(status);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll(Sort.by("createdAt").descending());
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id)
                .orElse(null);
    }
}
