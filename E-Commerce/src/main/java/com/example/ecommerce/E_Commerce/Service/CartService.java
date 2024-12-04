package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.DTOs.CartDTO;
import com.example.ecommerce.E_Commerce.Models.Cart;

public interface CartService
{
    public CartDTO addProductTOCart(Long productId, Integer quantity);


}
