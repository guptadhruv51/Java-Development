package com.example.ecommerce.E_Commerce.controller;


import com.example.ecommerce.E_Commerce.Models.Category;
import com.example.ecommerce.E_Commerce.Service.CategoryService;
import com.example.ecommerce.E_Commerce.Service.CategoryServiceImpl;
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
    public ResponseEntity<List<Category>> getAllCategories()
    {
        List<Category> categories=categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<String> addCategory(@Valid @RequestBody Category category)
    {
        this.categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("category created");
    }
    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId)
    {
        try {

            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
        catch (ResponseStatusException e)
        {
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }
    @PutMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category,
                                                 @PathVariable Long categoryId)
    {
        try
        {
            Category savedCategory = categoryService.updateCategory(category,categoryId);
            return new ResponseEntity<>("Updated Category with "+categoryId, HttpStatus.OK);
        }
        catch(ResponseStatusException e)
        {
            return new ResponseEntity<>(e.getReason(),e.getStatusCode());
        }
    }




}
