package org.example.courseonline_projetcfinfal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "file_lessons")
public class FileLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String file_url;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    Lesson lesson;
}
