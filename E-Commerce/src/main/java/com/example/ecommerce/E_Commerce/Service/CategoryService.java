package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.Models.Category;
import org.springframework.stereotype.Service;
import java.util.List;

public interface CategoryService
{
    List<Category> getAllCategories();
    void createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Category category, Long categoryId);
}
