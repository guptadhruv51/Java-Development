package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.DTOs.ProductDTO;
import com.example.ecommerce.E_Commerce.DTOs.ProductResponseDto;
import com.example.ecommerce.E_Commerce.Models.Product;

public interface ProductService {
    ProductDTO addProduct(Long categoryId, Product product);

    ProductResponseDto getAllProducts();

    ProductResponseDto searchByCategory(Long categoryId);

    ProductResponseDto searchByKeyword(String keyword);

    ProductDTO updateProduct(Long productId, Product product);

    ProductDTO deleteProduct(Long productId);
}
