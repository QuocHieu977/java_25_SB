package org.example.movieweb;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.example.movieweb.entity.Movie;
import org.example.movieweb.model.enums.MovieType;
import org.example.movieweb.model.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
class MovieWebApplicationTests {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    void save_movie() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        Random rd = new Random();

        for(int i = 0; i <150; i++) {
            String name = faker.name().fullName();
            Boolean status = faker.bool().bool();
            Movie movie = Movie.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .poster("https://placehold.co/600x400?text=" + name.substring(0,1).toUpperCase())
                    .description(faker.lorem().paragraph())
                    .releaseYear(faker.number().numberBetween(2020, 2024))
                    .rating(faker.number().randomDouble(1, 6,10))
                    .trailerUrl("https://www.youtube.com/embed/0Q-oYH62T-w?si=xba4LnEXJZRR8tQn")
                    .type(MovieType.values()[rd.nextInt(MovieType.values().length)])
                    .status(status)
                    .createdAt(LocalDateTime.now())
                    .updateAt(LocalDateTime.now())
                    .publishedAt(status ? LocalDateTime.now() : null)
                    .build();
            movieRepository.save(movie);
        }
    }

    @Test
    void test_method() {
        List<Movie> movies = movieRepository.findAll();
        System.out.println("Movies: " + movies.size());

        Movie movie1 = movieRepository.findById(1).orElse(null);

        //update
        movie1.setName("Conan movie 24");
        movieRepository.save(movie1);


        //delete
//        movieRepository.delete(movie1);
//        movieRepository.deleteById(10);
//        movieRepository.deleteAllById(List.of(4,2,3));

    }

    @Test
    public void testMethodQuery() {
        long countPhimBo = movieRepository.countByType(MovieType.PHIM_BO);
        System.out.println("so luong phim bo: " + countPhimBo);

        List<Movie> ratingMovies = movieRepository.findByRatingBetween(7.0,10.0);

        List<Movie> movieList = movieRepository.findTop5ByTypeAndStatusOrderByCreatedAtDescRatingAsc(MovieType.PHIM_BO, true);
        System.out.println(movieList);
    }
}
