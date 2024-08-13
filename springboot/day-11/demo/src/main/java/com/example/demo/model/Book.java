package com.example.demo.model;

import lombok.*;

import java.io.Serializable;

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
