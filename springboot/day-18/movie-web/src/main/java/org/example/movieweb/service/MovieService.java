package org.example.movieweb.service;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.Movie;
import org.example.movieweb.model.enums.MovieType;
import org.example.movieweb.model.repository.MovieRepository;
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

    public List<Movie> getMovieSortByRating(Boolean status) {
        return movieRepository.findTop4ByStatusOrderByRatingDesc(status);
    }
}
