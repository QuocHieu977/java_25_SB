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
@Table(name = "lessons")
public class Lesson {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  String title;
  String slug;

  @Column(columnDefinition = "MEDIUMTEXT")
  String content;

  String video_url;
  Integer displayOrder;
  Double duration;
  Boolean status;
  LocalDateTime created_at;
  LocalDateTime updated_at;
  LocalDateTime published_at;

  @ManyToOne
  @JoinColumn(name = "secton_id")
  Section section;
}
