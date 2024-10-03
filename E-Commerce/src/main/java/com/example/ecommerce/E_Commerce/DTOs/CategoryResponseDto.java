package com.example.ecommerce.E_Commerce.DTOs;


import com.example.ecommerce.E_Commerce.Models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDto
{
    private List<CategoryDTO> content;


}
