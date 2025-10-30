package com.spring.service;

import com.spring.dto.SalonDTO;
import com.spring.entity.Category;

import java.util.Set;

public interface CategoryService
{
    Category createCategory(Category category, SalonDTO salonDTO);
    Set<Category> getAllCategoryBySalonId(Long id);
    Category getCategoryById(Long id);
    void deleteCategoryById(Long id,Long salonId);

}
