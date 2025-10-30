package com.spring.controller;

import com.spring.entity.Category;
import com.spring.service.CategoryServiceImple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController
{
    private final CategoryServiceImple service;

    @GetMapping("/salon/{id}")
    public ResponseEntity<Set<Category>> getCategoriesBySalonId(@PathVariable Long id)
    {
        Set<Category> res=service.getAllCategoryBySalonId(id);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id)
    {
        Category res=service.getCategoryById(id);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
