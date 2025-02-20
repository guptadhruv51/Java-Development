package com.example.expense_tracker_app.Controller;


import com.example.expense_tracker_app.DTO.CategoryDTO;
import com.example.expense_tracker_app.Service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
@RestController

public class CategoryController
{
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/get/{CategoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("CategoryId") Long categoryId)
    {
         CategoryDTO categoryDTO=categoryService.getCategoryByID(categoryId);
         return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> createCategory( @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO savedCategory= categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAllCategories()
    {
        List<CategoryDTO> categoryDTOS=categoryService.getAllCategoryDTO();
        return new ResponseEntity<>(categoryDTOS,HttpStatus.OK);
    }


    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO updatedCategoryDTO=categoryService.updateCategory(categoryId,categoryDTO);
        return new ResponseEntity<>(updatedCategoryDTO,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId)
    {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted",HttpStatus.OK);
    }

}
