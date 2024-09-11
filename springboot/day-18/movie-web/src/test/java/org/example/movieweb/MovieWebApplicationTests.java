package org.example.movieweb;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.example.movieweb.entity.*;
import org.example.movieweb.model.enums.MovieType;
import org.example.movieweb.model.enums.UserRole;
import org.example.movieweb.model.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class MovieWebApplicationTests {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    @Test
    void save_user() {
        Faker faker = new Faker();
        for(int i = 0; i <50; i++) {
            String name = faker.name().fullName();
            User user = User.builder()
                    .name(name)
                    .email(faker.internet().emailAddress())
                    .avatar("https://placehold.co/200x200?text=" + name.substring(0, 1).toUpperCase())
                    .password("123")
                    .role(i == 0 || i == 1 ? UserRole.ADMIN : UserRole.USER)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    void save_genres() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 10; i++) {
            String name = faker.leagueOfLegends().champion();
            Genre genre = Genre.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            genreRepository.save(genre);
        }
    }

    @Test
    void save_countries() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for(int i = 0; i < 10; i++) {
            String name = faker.country().name();
            Country country = Country.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            countryRepository.save(country);
        }
    }

    @Test
    void save_actors() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 100; i++) {
            String name = faker.name().fullName();
            Actor actor = Actor.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .avatar("https://placehold.co/200x200?text=" + name.substring(0, 1).toUpperCase())
                    .bio(faker.lorem().paragraph())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            actorRepository.save(actor);
        }
    }

    @Test
    void save_directors() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 20; i++) {
            String name = faker.name().fullName();
            Director director = Director.builder()
                    .name(name)
                    .slug(slugify.slugify(name))
                    .avatar("https://placehold.co/200x200?text=" + name.substring(0, 1).toUpperCase())
                    .bio(faker.lorem().paragraph())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            directorRepository.save(director);
        }
    }

    @Test
    void save_movie() {
        Faker faker = new Faker();
        Slugify slugify = Slugify.builder().build();
        Random rd = new Random();

        List<Country> countries = countryRepository.findAll();
        List<Actor> actors = actorRepository.findAll();
        List<Director> directors = directorRepository.findAll();
        List<Genre> genres = genreRepository.findAll();

        for(int i = 0; i <150; i++) {
            //random 1 country
            Country rdCountry = countries.get(rd.nextInt(countries.size()));

            // random 1 -> 3 genres
            List<Genre> rdGenres = new ArrayList<>();
            for(int j = 0; j <rd.nextInt(3) + 1; j++) {
                Genre rdGenre = genres.get(rd.nextInt(genres.size()));
                if(!rdGenres.contains(rdGenre)) {
                    rdGenres.add(rdGenre);
                }
            }

            // Random 5 -> 7 actors
            List<Actor> rdActors = new ArrayList<>();
            for (int j = 0; j < rd.nextInt(3) + 5; j++) {
                Actor rdActor = actors.get(rd.nextInt(actors.size()));
                if (!rdActors.contains(rdActor)) {
                    rdActors.add(rdActor);
                }
            }

            // Random 1 -> 3 directors
            List<Director> rdDirectors = new ArrayList<>();
            for (int j = 0; j < rd.nextInt(3) + 1; j++) {
                Director rdDirector = directors.get(rd.nextInt(directors.size()));
                if (!rdDirectors.contains(rdDirector)) {
                    rdDirectors.add(rdDirector);
                }
            }

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
                    .country(rdCountry)
                    .actors(rdActors)
                    .directors(rdDirectors)
                    .genres(rdGenres)
                    .build();
            movieRepository.save(movie);
        }
    }

    @Test
    void save_blogs() {
        Faker faker = new Faker();
        Random rd = new Random();
        Slugify slugify = Slugify.builder().build();

        List<User> users = userRepository.findByRole(UserRole.ADMIN);

        for (int i = 0; i < 100; i++) {
            // random 1 user
            User rdUser = users.get(rd.nextInt(users.size()));

            String title = faker.book().title();
            boolean status = rd.nextInt(2) == 0;
            Blog blog = Blog.builder()
                    .title(title)
                    .slug(slugify.slugify(title))
                    .description(faker.lorem().paragraph())
                    .content(faker.lorem().paragraph(100))
                    .status(status)
                    .thumbnail("https://placehold.co/600x400?text=" + title.substring(0,1).toUpperCase())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .publishedAt(status ? LocalDateTime.now() : null)
                    .user(rdUser)
                    .build();

            blogRepository.save(blog);
        }
    }

    @Test
    void save_reviews() {
        Faker faker = new Faker();
        Random rd = new Random();

        List<Movie> movies = movieRepository.findAll();
        List<User> users = userRepository.findAll();

        for (Movie movie : movies) {
            // Create 10 -> 20 reviews for each movie
            for (int i = 0; i < rd.nextInt(11) + 10; i++) {
                // Random 1 user
                User rdUser = users.get(rd.nextInt(users.size()));

                Review review = Review.builder()
                        .content(faker.lorem().paragraph())
                        .rating(rd.nextInt(6) + 5)
                        .movie(movie)
                        .user(rdUser)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                reviewRepository.save(review);
            }
        }
    }

    @Test
    void save_episodes() {
        Faker faker = new Faker();
        Random rd = new Random();

        List<Movie> movies = movieRepository.findAll();

        for (Movie movie : movies) {
            if (movie.getType().equals(MovieType.PHIM_BO)) {
                // Random 5 -> 10 episodes for each movie
                for (int i = 0; i < rd.nextInt(6) + 5; i++) {
                    Episode episode = Episode.builder()
                            .name("Tap " + (i + 1))
                            .duration(rd.nextInt(31) + 30)
                            .displayOrder(i + 1)
                            .videoUrl("https://www.youtube.com/embed/gCUg6Td5fgQ?si=OCtNkpFF03gq03ny")
                            .movie(movie)
                            .status(true)
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .publishedAt(LocalDateTime.now())
                            .build();
                    episodeRepository.save(episode);
                }
            } else {
                Episode episode = Episode.builder()
                        .name("Tap full")
                        .duration(rd.nextInt(91) + 30)
                        .displayOrder(1)
                        .videoUrl("https://www.youtube.com/embed/gCUg6Td5fgQ?si=OCtNkpFF03gq03ny")
                        .movie(movie)
                        .status(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .publishedAt(LocalDateTime.now())
                        .build();
                episodeRepository.save(episode);
            }
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
//        long countPhimBo = movieRepository.countByType(MovieType.PHIM_BO);
//        System.out.println("so luong phim bo: " + countPhimBo);
//
//        List<Movie> ratingMovies = movieRepository.findByRatingBetween(7.0,10.0);
//
//        List<Movie> movieList = movieRepository.findTop5ByTypeAndStatusOrderByCreatedAtDescRatingAsc(MovieType.PHIM_BO, true);
//
//
//        List<Movie> movies = movieRepository.findByStatus(true, Sort.by("name", "releaseYear").descending());
//        System.out.println(movies);

        Pageable pageable = PageRequest.of(0, 10);
        Page<Movie> pageData = movieRepository.findByStatus(true, pageable);

        // tổng tat cả số page
        System.out.println("Total page: " + pageData.getTotalPages());

        // số phần tử trong 1 trang
        System.out.println("Total elements: " + pageData.getTotalElements());

        // xem có trang tiếp theo hay không: isFirst
        System.out.println("Is first: "+ pageData.isFirst());

        // duyệt qua các phần tử
        pageData.getContent().forEach(System.out::println);

    }
}
