package com.spring.controller;

import com.spring.dto.SalonDTO;
import com.spring.entity.Category;
import com.spring.service.CategoryServiceImple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salon/owner")
@RequiredArgsConstructor
public class SalonCategoryController
{
    private final CategoryServiceImple service;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category category)
    {
        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(1L);
        Category savedCategory=service.createCategory(category,salonDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id)
    {
        SalonDTO salonDTO=new SalonDTO();
        salonDTO.setId(1L);
        service.deleteCategoryById(id,salonDTO.getId());
        return "category is deleted successfully";
    }
}
