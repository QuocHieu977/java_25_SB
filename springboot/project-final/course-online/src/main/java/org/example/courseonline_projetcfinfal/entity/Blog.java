package org.example.courseonline_projetcfinfal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;
    String slug;

    @Column(columnDefinition = "MEDIUMTEXT")
    String description;

    @Column(columnDefinition = "TEXT")
    String content;

    String thumbnail;
    Boolean status;
    LocalDateTime created_at;
    LocalDateTime updated_at;
    LocalDateTime published_at;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
