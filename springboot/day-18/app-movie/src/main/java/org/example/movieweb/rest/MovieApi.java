package org.example.movieweb.rest;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.entity.Movie;
import org.example.movieweb.model.request.CreateMovieRequest;
import org.example.movieweb.model.request.UpsertMovieRequest;
import org.example.movieweb.model.response.ErrorResponse;
import org.example.movieweb.model.response.FileResponse;
import org.example.movieweb.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin/movie")
@RequiredArgsConstructor
public class MovieApi {
    private final MovieService movieService;

    @PostMapping()
    public ResponseEntity<?> createMovie(@RequestBody CreateMovieRequest request) {
        try {
            Movie movie = movieService.createMovie(request);
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody UpsertMovieRequest request,
                                          @PathVariable Integer id) {
        try {
            Movie movie = movieService.updateMovie(id, request);
            return ResponseEntity.ok(movie);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/{id}/upload-poster")
    public ResponseEntity<?> uploadPoster(@RequestParam MultipartFile file,
                                         @PathVariable Integer id) {
        try {
            String path = movieService.uploadPoster(id, file);
            FileResponse fileResponse = FileResponse.builder()
                    .url(path)
                    .build();
            return ResponseEntity.ok(fileResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
