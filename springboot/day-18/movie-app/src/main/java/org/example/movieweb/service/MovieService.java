package org.example.movieweb.service;

import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.*;
import org.example.movieweb.model.enums.MovieType;
import org.example.movieweb.model.request.CreateMovieRequest;
import org.example.movieweb.model.request.UpsertMovieRequest;
import org.example.movieweb.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;
    private final CloudinaryService cloudinaryService;

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

    public Movie updateMovie(Integer id, UpsertMovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        List<Genre> genres = genreRepository.findAllById(request.getGenreIds());
        List<Director> directors = directorRepository.findAllById(request.getDirectorIds());
        List<Actor> actors = actorRepository.findAllById(request.getActorIds());

        Slugify slugify = Slugify.builder().build();

        movie.setName(request.getName());
        movie.setSlug(slugify.slugify(request.getName()));
        movie.setTrailerUrl(request.getTrailerUrl());
        movie.setDescription(request.getDescription());
        movie.setGenres(genres);
        movie.setActors(actors);
        movie.setDirectors(directors);
        movie.setStatus(request.getStatus());
        movie.setType(request.getType());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setCountry(country);
        movie.setUpdateAt(LocalDateTime.now());
        movie.setPublishedAt(request.getStatus() ? LocalDateTime.now() : null);

        return movieRepository.save(movie);
    }

    public String uploadPoster(Integer id, MultipartFile file) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        try {
            Map map = cloudinaryService.uploadFile(file, "java-25-movie");
            String path = map.get("url").toString();
            movie.setPoster(path);
            movieRepository.save(movie);
            return path;
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload");
        }
    }

    public Movie createMovie(CreateMovieRequest request) {
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        List<Genre> genres = genreRepository.findAllById(request.getGenreIds());
        List<Director> directors = directorRepository.findAllById(request.getDirectorIds());
        List<Actor> actors = actorRepository.findAllById(request.getActorIds());

        Slugify slugify = Slugify.builder().build();
        Movie movie = Movie.builder()
                .name(request.getName())
                .slug(slugify.slugify(request.getName()))
                .poster("https://placehold.co/600x400?text=" + request.getName().substring(0,1).toUpperCase())
                .trailerUrl(request.getTrailerUrl())
                .description(request.getDescription())
                .genres(genres)
                .actors(actors)
                .directors(directors)
                .status(request.getStatus())
                .type(request.getType())
                .releaseYear(request.getReleaseYear())
                .country(country)
                .createdAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .publishedAt(request.getStatus() ? LocalDateTime.now() : null)
                .build();

        return movieRepository.save(movie);
    }
}
