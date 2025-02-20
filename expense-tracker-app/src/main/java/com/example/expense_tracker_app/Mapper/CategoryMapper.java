package com.example.expense_tracker_app.Mapper;

import com.example.expense_tracker_app.DTO.CategoryDTO;
import com.example.expense_tracker_app.Entity.Category;

public class CategoryMapper
{

    // Map Category DTO to Category
    public static Category mapToCategory(CategoryDTO categoryDto){
        return new Category(
                categoryDto.id(),
                categoryDto.name()
        );
    }

    // Map Category entity to CategoryDto
    public static CategoryDTO mapToCategoryDto(Category category){
        return new CategoryDTO(
                category.getId(),
                category.getName()
        );
    }
}
