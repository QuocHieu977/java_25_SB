package org.example.courseonline_projetcfinfal.service;

import lombok.RequiredArgsConstructor;
import org.example.courseonline_projetcfinfal.entity.Category;
import org.example.courseonline_projetcfinfal.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
