package com.example.expense_tracker_app.Service;

import com.example.expense_tracker_app.DTO.CategoryDTO;
import com.example.expense_tracker_app.Entity.Category;
import com.example.expense_tracker_app.Mapper.CategoryMapper;
import com.example.expense_tracker_app.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements  CategoryService
{
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category= CategoryMapper.mapToCategory(categoryDTO);
        Category savedCategory=categoryRepo.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDTO getCategoryByID(Long id) {
        Category category= categoryRepo.findById(id).orElseThrow(()->new RuntimeException("Category not found for id " + id));

        return  CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDTO> getAllCategoryDTO() {
        List<Category> categories=categoryRepo.findAll();
        return categories.stream().map((category -> CategoryMapper.mapToCategoryDto(category))).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategory(Long categoryId, CategoryDTO categoryDTO)
    {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("category not found"));

        category.setName(categoryDTO.name());
        Category savedCategory= categoryRepo.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {

        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new RuntimeException("category not found"));
        categoryRepo.delete(category);
    }
}
