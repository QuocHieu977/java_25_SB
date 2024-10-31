package org.example.courseonline_projetcfinfal.controller;

import lombok.RequiredArgsConstructor;
import org.example.courseonline_projetcfinfal.entity.Category;
import org.example.courseonline_projetcfinfal.entity.Course;
import org.example.courseonline_projetcfinfal.entity.Review;
import org.example.courseonline_projetcfinfal.entity.Section;
import org.example.courseonline_projetcfinfal.service.CategoryService;
import org.example.courseonline_projetcfinfal.service.CourseService;
import org.example.courseonline_projetcfinfal.service.ReviewService;
import org.example.courseonline_projetcfinfal.service.SectionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

  private final CourseService courseService;
  private final CategoryService categoryService;
  private final ReviewService reviewService;
  private final SectionService sectionService;

  @ModelAttribute("categories")
  public List<Category> getCategories() {
    return categoryService.getAllCategory();
  }

  @GetMapping
  public String getHomePage(Model model) {
    List<Course> listCoursePopular = courseService.getCourseByRating(true);
    List<Course> listCourseNew = courseService.getCourseNew(true);

    model.addAttribute("listCoursePopular", listCoursePopular);
    model.addAttribute("listCourseNew", listCourseNew);

    return "web/index";
  }

  @GetMapping("/khoa-hoc/{id}/{slug}")
  public String getCourseDetails(Model model, @PathVariable Integer id, @PathVariable String slug) {
    Course course = courseService.getCourseDetails(id, slug);
    model.addAttribute("course", course);

    List<Review> reviews = reviewService.getReviewsByCourseId(course.getId());
    model.addAttribute("reviews", reviews);

    List<Section> sections = sectionService.getSectionByCourseId(course.getId());
    model.addAttribute("sections", sections);
    
    return "web/chi-tiet-khoa-hoc";
  }
}
