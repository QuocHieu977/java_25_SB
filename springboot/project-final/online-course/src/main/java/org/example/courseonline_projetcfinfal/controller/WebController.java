package org.example.courseonline_projetcfinfal.controller;

import lombok.RequiredArgsConstructor;
import org.example.courseonline_projetcfinfal.entity.Course;
import org.example.courseonline_projetcfinfal.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final CourseService courseService;

    @GetMapping
    public String getHomePage(Model model) {
        List<Course> listCourseByRating = courseService.getCourseByRating(true);
        List<Course> listCourseNew = courseService.getCourseNew(true);
        model.addAttribute("listCourseByRating", listCourseByRating);
        model.addAttribute("listCourseNew", listCourseNew);

        System.out.println("listCourseByRating" + listCourseByRating.size());
        System.out.println("listCourseNew" + listCourseNew.size());

        return "web/index";
    }
}
