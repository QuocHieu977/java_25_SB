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
@Table(name = "sections")
public class Section {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Integer id;

  String title;
  String slug;

  @Column(columnDefinition = "MEDIUMTEXT")
  String description;

  Integer displayOrder;
  Boolean status;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "course_id")
  Course course;
}
