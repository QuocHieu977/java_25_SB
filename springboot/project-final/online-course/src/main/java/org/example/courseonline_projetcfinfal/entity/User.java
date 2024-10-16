package org.example.courseonline_projetcfinfal.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.courseonline_projetcfinfal.model.enums.UserRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String full_name;

    @Column(unique = true, nullable = false)
    String email;

    String phone;
    String avatar;
    String password;
    String address;
    LocalDate date_of_birth;
    UserRole role;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
