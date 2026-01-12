package com.example.spring_security_blog_tutorial.controllers;


import com.example.spring_security_blog_tutorial.domain.dtos.CategoryDto;
import com.example.spring_security_blog_tutorial.domain.dtos.CreateCategoryRequest;
import com.example.spring_security_blog_tutorial.domain.entites.Category;
import com.example.spring_security_blog_tutorial.mapper.CategoryMapper;
import com.example.spring_security_blog_tutorial.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        var categories = this.categoryService.listCategories()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
        return  ResponseEntity.ok(categories);
    }


    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest) {
        Category category = categoryMapper.toEntity(createCategoryRequest);
        var savedCategory =  categoryService.createCategory(category);
        return ResponseEntity.ok(categoryMapper.toDto(savedCategory));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable UUID id
            ) {

        categoryService.deleteCategory(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
