package com.example.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Room {
    // cách 1: sử dụng Autowired
//    @Autowired
//    Student student;
//
//    @Autowired
//    Teacher teacher;

    // cách 2: sử dụng qua constructor (@AllArgsConstructor)
    Student student;
    Teacher teacher;

    public Room(Student student,@Qualifier("teacher2")  Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
    }
}

