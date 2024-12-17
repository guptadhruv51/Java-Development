package com.example.ecommerce.E_Commerce.repository;

import com.example.ecommerce.E_Commerce.Models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
}
