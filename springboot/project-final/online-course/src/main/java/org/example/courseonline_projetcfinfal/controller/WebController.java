package org.example.courseonline_projetcfinfal.controller;

import lombok.RequiredArgsConstructor;
import org.example.courseonline_projetcfinfal.entity.Category;
import org.example.courseonline_projetcfinfal.entity.Course;
import org.example.courseonline_projetcfinfal.service.CategoryService;
import org.example.courseonline_projetcfinfal.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final CourseService courseService;
    private final CategoryService categoryService;

    @GetMapping
    public String getHomePage(Model model) {
        List<Course> listCoursePopular = courseService.getCourseByRating(true);
        List<Course> listCourseNew = courseService.getCourseNew(true);
        List<Category> categories = categoryService.getAllCategory();

        model.addAttribute("categories", categories);
        model.addAttribute("listCoursePopular", listCoursePopular);
        model.addAttribute("listCourseNew", listCourseNew);

        return "web/index";
    }

    @GetMapping("/khoa-hoc/{id}/{slug}")
    public String getCourseDetails(Model model, @PathVariable Integer id, @PathVariable String slug) {
        return "web/chi-tiet-khoa-hoc";
    }
}
