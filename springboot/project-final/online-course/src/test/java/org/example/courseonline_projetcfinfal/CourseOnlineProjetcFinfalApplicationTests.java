package org.example.courseonline_projetcfinfal;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.example.courseonline_projetcfinfal.entity.*;
import org.example.courseonline_projetcfinfal.model.enums.UserRole;
import org.example.courseonline_projetcfinfal.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class CourseOnlineProjetcFinfalApplicationTests {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BlogRepository blogRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private SectionRepository sectionRepository;


  @Test
  void contextLoads() {
  }


  LocalDate convertDateToLocalDate(Date date) {
    if (date == null) {
      return null;
    }
    return date.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  UserRole assignRandomRole() {
    Random random = new Random();
    int randomNumber = random.nextInt(2); // Random số từ 0 đến 1 (2 roles)

    // Gán vai trò dựa trên giá trị ngẫu nhiên
    switch (randomNumber) {
      case 0:
        return UserRole.STUDENT;
      case 1:
        return UserRole.TEACHER;
      default:
        return UserRole.STUDENT; // Default role (nếu cần)
    }
  }

  @Test
  void save_user() {
    Faker faker = new Faker();
    for (int i = 0; i < 50; i++) {
      String name = faker.name().fullName();
      User user = User.builder()
          .full_name(name)
          .email(faker.internet().emailAddress())
          .phone(faker.phoneNumber().cellPhone())
          .avatar("https://placehold.co/200x200?text=" + name.substring(0, 1).toUpperCase())
          .password("123")
          .address(faker.address().fullAddress())
          .date_of_birth(convertDateToLocalDate(faker.date().birthday()))
          .role(i == 0 || i == 1 ? UserRole.ADMIN : assignRandomRole())
          .createdAt(LocalDateTime.now())
          .updatedAt(LocalDateTime.now())
          .build();
      userRepository.save(user);
    }
  }

  @Test
  void save_blogs() {
    Faker faker = new Faker();
    Random rd = new Random();
    Slugify slugify = Slugify.builder().build();

    List<User> users = userRepository.findByRole(UserRole.ADMIN);

    for (int i = 0; i < 100; i++) {
      // random 1 user
      User rdUser = users.get(rd.nextInt(users.size()));

      String title = faker.book().title();
      // status =0 = true
      boolean status = rd.nextInt(2) == 0;
      Blog blog = Blog.builder()
          .title(title)
          .slug(slugify.slugify(title))
          .description(faker.lorem().paragraph())
          .content(faker.lorem().paragraph(100))
          .thumbnail("https://placehold.co/600x400?text=" + title.substring(0, 1).toUpperCase())
          .status(status)
          .createdAt(LocalDateTime.now())
          .updatedAt(LocalDateTime.now())
          .publishedAt(status ? LocalDateTime.now() : null)
          .user(rdUser)
          .build();

      blogRepository.save(blog);
    }
  }

  @Test
  void save_categories() {
    Faker faker = new Faker();
    Slugify slugify = Slugify.builder().build();
    Random rd = new Random();
    List<Category> parentCategories = new ArrayList<>();

    for (int i = 0; i < 13; i++) {
      String name = faker.name().fullName();
      Category category;

      if (i < 8) {
        category = Category.builder()
            .name(name)
            .slug(slugify.slugify(name))
            .description(faker.lorem().paragraph())
            .parent_int(null)
            .build();
        parentCategories.add(category);
      } else {
        Category parentCategory = parentCategories.get(rd.nextInt(parentCategories.size()));

        category = Category.builder()
            .name(name)
            .slug(slugify.slugify(name))
            .description(faker.lorem().paragraph())
            .parent_int(parentCategory.getId())
            .build();

      }

      categoryRepository.save(category);
    }
  }


  @Test
  void save_courses() {
    Faker faker = new Faker();
    Random rd = new Random();
    Slugify slugify = Slugify.builder().build();

    List<User> teachers = userRepository.findByRole(UserRole.TEACHER);
    List<Category> categories = categoryRepository.findAll();

    for (int i = 0; i < 100; i++) {
      String title = faker.book().title();
      Category rdCategory = categories.get(rd.nextInt(categories.size()));
      Boolean status = rd.nextInt(2) == 0;

      List<User> rdTeachers = new ArrayList<>();
      for (int j = 0; j < rd.nextInt(3) + 1; j++) {
        User rdTeacher = teachers.get(rd.nextInt(teachers.size()));
        if (!rdTeachers.contains(rdTeacher)) {
          rdTeachers.add(rdTeacher);
        }
      }

      Course course = Course.builder()
          .title(title)
          .slug(slugify.slugify(title))
          .description(faker.lorem().paragraph())
          .thumbnail("https://placehold.co/400x300?text=" + title.substring(0, 1).toUpperCase())
          .status(status)
          .rating(faker.number().randomDouble(1, 1, 5))
          .price(faker.number().randomDouble(1, 2000, 5000))
          .priceDiscount(faker.number().randomDouble(1, 500, 2000))
          .createdAt(LocalDateTime.now())
          .updatedAt(LocalDateTime.now())
          .publishedAt(LocalDateTime.now())
          .category(rdCategory)
          .teachers(rdTeachers)
          .build();
      courseRepository.save(course);
    }
  }

  @Test
  void save_review() {
    Faker faker = new Faker();
    Random rd = new Random();

    List<Course> courses = courseRepository.findAll();
    List<User> users = userRepository.findAll();

    for (Course course : courses) {
      // Create 10 -> 20 reviews for each movie
      for (int i = 0; i < rd.nextInt(11) + 10; i++) {
        // Random 1 user
        User rdUser = users.get(rd.nextInt(users.size()));

        Review review = Review.builder()
            .content(faker.lorem().paragraph())
            .rating(rd.nextInt(5) + 1)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .user(rdUser)
            .course(course)
            .build();
        reviewRepository.save(review);
      }
    }
  }

  @Test
  void save_section() {
    Faker faker = new Faker();
    Random rd = new Random();
    Slugify slugify = Slugify.builder().build();
    List<Course> courses = courseRepository.findAll();

    for (Course course : courses) {
      String title = faker.book().title();

      for (int i = 1; i < 10; i++) {
        Section section = Section.builder()
            .title(title)
            .slug(slugify.slugify(title))
            .description(faker.lorem().paragraph(100))
            .displayOrder(i)
            .status(true)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .course(course)
            .build();
        sectionRepository.save(section);
      }
    }
  }

  @Test
  void save_file_lesson() {

  }


}
