package com.example.ecommerce.E_Commerce.controller;


import com.example.ecommerce.E_Commerce.Config.AppConstants;
import com.example.ecommerce.E_Commerce.DTOs.CategoryDTO;
import com.example.ecommerce.E_Commerce.DTOs.CategoryResponseDto;
import com.example.ecommerce.E_Commerce.Service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CategoryController
{
    @Autowired
    CategoryServiceImpl categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<CategoryResponseDto> getAllCategories(
            @RequestParam(name="pageNumber",required = false,defaultValue = AppConstants.PAGE_Number) Integer pageNumber,
            @RequestParam(name="pageSize",required = false,defaultValue = AppConstants.PAGE_Size) Integer pageSize,
            @RequestParam(name="sortBy",required = false,defaultValue = AppConstants.Sort_Categories_By) String sortBy,
            @RequestParam(name="=sortOrder",required = false,defaultValue = AppConstants.sortOrder) String sortOrder)
    {
        CategoryResponseDto categoryResponseDto=categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
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
