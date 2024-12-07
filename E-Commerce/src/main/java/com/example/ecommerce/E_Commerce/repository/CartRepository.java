package com.example.ecommerce.E_Commerce.repository;

import com.example.ecommerce.E_Commerce.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart,Long>
{

    @Query("Select c from Cart c where c.user.email=?1")
    Cart findCartByEmail(String email);

    @Query("Select c from Cart c where c.user.email=?1 and c.cartId=?2")
    Cart findCartByEmailAndCartId(String emailId, Long cartId);
}
