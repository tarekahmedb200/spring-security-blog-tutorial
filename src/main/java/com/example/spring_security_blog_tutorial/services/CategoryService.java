package com.example.spring_security_blog_tutorial.services;

import com.example.spring_security_blog_tutorial.domain.entites.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
    void deleteCategory(UUID id);
    Category getCategoryById(UUID id);
}
