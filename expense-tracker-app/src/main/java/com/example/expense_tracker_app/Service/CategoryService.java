package com.example.expense_tracker_app.Service;

import com.example.expense_tracker_app.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService
{
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategoryByID(Long id);
    List<CategoryDTO> getAllCategoryDTO();
    CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO);
    void deleteCategory(Long categoryId);

}
