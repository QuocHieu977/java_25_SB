package org.example.movieweb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.movieweb.model.enums.MovieType;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true) // name không được để trống
    String name;

    @Column(unique = true, nullable = false)
    String slug;
    String poster;

    @Column(columnDefinition = "TEXT") // set cột description text lưu trữ được hơn varchar
    String description;
    Integer releaseYear;
    Double rating;
    String trailerUrl;

    @Enumerated(EnumType.STRING)
    MovieType type;
    Boolean status;
    LocalDateTime createdAt;
    LocalDateTime updateAt;
    LocalDateTime publishedAt;

    @ManyToOne
    @JoinColumn(name="country_id")
    Country country;

    @ManyToMany
    @JoinTable(
            name="moive_genre",
            joinColumns = @JoinColumn(name="moive_id"),
            inverseJoinColumns = @JoinColumn(name="genre_id")
    )
    List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name="moive_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id")
    )
    List<Actor> actors;

    @ManyToMany
    @JoinTable(
            name = "movie_director",
            joinColumns = @JoinColumn(name="moive_id"),
            inverseJoinColumns = @JoinColumn(name="director_id")
    )
    List<Director> directors;
}
