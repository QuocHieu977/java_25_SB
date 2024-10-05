package org.example.movieweb.controller;

import lombok.RequiredArgsConstructor;
import org.example.movieweb.repository.ActorRepository;
import org.example.movieweb.repository.DirectorRepository;
import org.example.movieweb.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/movie")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    private final CountryService countryService;
    private final GenreService genreService;
    private final DirectorService directorService;
    private final ActorService actorService;

    @GetMapping
    public String getHomepage(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/movie/index";
    }

    @GetMapping("/create")
    public String getCreatePage(Model model) {
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("directors", directorService.getAllDirectors());
        model.addAttribute("actors", actorService.getAllActors());
        return "admin/movie/create";
    }

    @GetMapping("/{id}/detail")
    public String getDetailPage(Model model ,@PathVariable Integer id) {
        model.addAttribute("movie", movieService.getMovieById(id));
        return "admin/movie/detail";
    }
}
