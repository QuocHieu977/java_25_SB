package org.example.courseonline_projetcfinfal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WebController {
    @GetMapping
    public String getHomePage() {
        // to-do
        return "web/index";
    }
}
