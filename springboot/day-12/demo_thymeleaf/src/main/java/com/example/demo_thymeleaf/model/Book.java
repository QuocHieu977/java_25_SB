package com.example.demo_thymeleaf.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
}
