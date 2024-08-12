package com.example.demo;

import com.example.demo.model.Room;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		Student student = context.getBean(Student.class);
		System.out.println(student);
		student.getVehicle().run();

		Teacher teacher1 = context.getBean("teacher2", Teacher.class);
		System.out.println("teacher: " + teacher1);

		Room room = context.getBean(Room.class);
		System.out.println(room);

	}
}
