package com.example.ecommerce.E_Commerce.repository;

import com.example.ecommerce.E_Commerce.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepo extends JpaRepository<CartItem,Long>
{
    @Query("Select ci from CartItem ci where ci.cart.cartId=?1 and ci.product.productId=?2")
    CartItem findCartItemByProductAndCartId(Long cartId, Long productId);
}
