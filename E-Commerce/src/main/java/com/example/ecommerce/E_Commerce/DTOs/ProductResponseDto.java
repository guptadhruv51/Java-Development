package com.example.ecommerce.E_Commerce.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto
{
    private List<ProductDTO>content;
}
