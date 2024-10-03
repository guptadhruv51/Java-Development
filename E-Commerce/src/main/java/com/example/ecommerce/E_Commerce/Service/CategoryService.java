package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.DTOs.CategoryDTO;
import com.example.ecommerce.E_Commerce.DTOs.CategoryResponseDto;
import com.example.ecommerce.E_Commerce.Models.Category;
import org.springframework.stereotype.Service;
import java.util.List;

public interface CategoryService
{
    CategoryResponseDto getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId);
}
