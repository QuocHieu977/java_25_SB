package org.example.movieweb.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.movieweb.model.enums.UserRole;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @Column(unique = true, nullable = false)
    String email;
    String avatar;
    String password;

    @Enumerated(EnumType.STRING)
    UserRole role;

    Boolean isActive;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
