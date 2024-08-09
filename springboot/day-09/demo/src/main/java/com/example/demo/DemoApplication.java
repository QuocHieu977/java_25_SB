package com.example.demo;

import com.example.demo.model.Book;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Faker faker = new Faker();
		System.out.println(faker.name().fullName());

		Book book1 = new Book();

		book1.setId(1);
		book1.setTitle("harry potter");
		book1.setAuthor("jonh ken");
		book1.setYear(1999);

		Book book2 = Book.builder()
				.id(1)
				.year(1999)
				.title("fasfsa")
				.build();

		System.out.println(book1);
		System.out.println(book2);
	}
}
