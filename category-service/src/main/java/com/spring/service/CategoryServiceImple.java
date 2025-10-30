package com.spring.service;

import com.spring.dto.SalonDTO;
import com.spring.entity.Category;
import com.spring.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImple implements CategoryService
{
private final CategoryRepository repository;
    @Override
    public Category createCategory(Category category, SalonDTO salonDTO)
    {
        Category category1=new Category();
        category1.setCategoryName(category.getCategoryName());
        category1.setImage(category.getImage());
        category1.setSalonId(salonDTO.getId());
        return  repository.save(category1);

    }

    @Override
    public Set<Category> getAllCategoryBySalonId(Long id)
    {
        return repository.findBySalonId(id);
    }

    @Override
    public Category getCategoryById(Long id)
    {
        Optional<Category> opt=repository.findById(id);
        if(opt.isPresent())
        {
            return  opt.get();
        }
      throw  new RuntimeException("category id is not exist");
    }

    @Override
    public void deleteCategoryById(Long id, Long salonId)
    {
           Category category=getCategoryById(id);
           if(category.getSalonId().equals(salonId))
           {
               repository.deleteById(id);
           }
           else {
               throw new RuntimeException("salon id is not found for deletion");
           }
    }


}
