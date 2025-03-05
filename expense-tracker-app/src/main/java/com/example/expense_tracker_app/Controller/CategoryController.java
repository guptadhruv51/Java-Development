package com.example.expense_tracker_app.Controller;


import com.example.expense_tracker_app.DTO.CategoryDTO;
import com.example.expense_tracker_app.Service.CategoryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
@RestController
@Tag(
        name="CRUD REST APIS FOR CATEGORY RESOURCE",
        description = "CRUD for Category Resource- CREATE, UPDATE, GET, DELETE"
)

public class CategoryController
{
    @Autowired
    CategoryServiceImpl categoryService;


    @Operation(
            summary="get category REST API"
    )
    @ApiResponse
            (
                    responseCode = "201"
            )
    @GetMapping("/get/{CategoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("CategoryId") Long categoryId)
    {
         CategoryDTO categoryDTO=categoryService.getCategoryByID(categoryId);
         return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
    }
    @Operation(
            summary="Create category REST API"
    )
    @ApiResponse
            (
                    responseCode = "201",
                    description = "201 created"
            )
    @PostMapping("/add")
    public ResponseEntity<CategoryDTO> createCategory( @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO savedCategory= categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @Operation(
            summary="Get all categories REST API"
    )
    @ApiResponse
            (
                    responseCode = "200",
                    description = "200 OK"
            )
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAllCategories()
    {
        List<CategoryDTO> categoryDTOS=categoryService.getAllCategoryDTO();
        return new ResponseEntity<>(categoryDTOS,HttpStatus.OK);
    }


    @Operation(
            summary="Update a category REST API"
    )
    @ApiResponse
            (
                    responseCode = "200",
                    description = "200 OK"
            )

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryDTO categoryDTO)
    {
        CategoryDTO updatedCategoryDTO=categoryService.updateCategory(categoryId,categoryDTO);
        return new ResponseEntity<>(updatedCategoryDTO,HttpStatus.OK);
    }

    @Operation(
            summary="Delete category REST API"
    )
    @ApiResponse
            (
                    responseCode = "200",
                    description = "200 OK"
            )

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId)
    {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>("Category deleted",HttpStatus.OK);
    }

}
