package com.example.demo;

import com.example.demo.model.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitBean {
    @Bean(name = "teacher1")
    public Teacher teacher1() {
        return new Teacher(1, "Bui Hien");
    }

    @Bean(name = "teacher2")
    public Teacher teacher2() {
        return new Teacher(1, "Thầy Hieu DZ");
    }
}
