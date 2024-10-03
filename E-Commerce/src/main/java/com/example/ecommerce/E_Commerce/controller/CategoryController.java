package com.example.ecommerce.E_Commerce.controller;


import com.example.ecommerce.E_Commerce.DTOs.CategoryDTO;
import com.example.ecommerce.E_Commerce.DTOs.CategoryResponseDto;
import com.example.ecommerce.E_Commerce.Models.Category;
import com.example.ecommerce.E_Commerce.Service.CategoryService;
import com.example.ecommerce.E_Commerce.Service.CategoryServiceImpl;
import com.example.ecommerce.E_Commerce.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController
{
    @Autowired
    CategoryServiceImpl categoryService;
    



    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponseDto> getAllCategories()
    {
        CategoryResponseDto categoryResponseDto=categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponseDto,HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO)
    {
       CategoryDTO categoryDTO1= this.categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(categoryDTO1,HttpStatus.CREATED);
    }
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId)
    {

            CategoryDTO status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);

    }
    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,
                                                 @PathVariable Long categoryId)
    {
        CategoryDTO savedCategory = categoryService.updateCategory(categoryDTO,categoryId);
        return new ResponseEntity<>(savedCategory, HttpStatus.OK);

    }




}
