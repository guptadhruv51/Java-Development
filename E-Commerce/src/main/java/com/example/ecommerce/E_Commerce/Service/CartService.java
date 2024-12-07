package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.DTOs.CartDTO;
import com.example.ecommerce.E_Commerce.Models.Cart;

import java.util.List;

public interface CartService
{
    public CartDTO addProductTOCart(Long productId, Integer quantity);


    List<CartDTO> getAllCarts();

    CartDTO getCart(String emailId, Long cartId);

    CartDTO updateProductQuantityInCart(Long productId, Integer operation);

    String deleteProductFromCart(Long cartId, Long productId);
}
