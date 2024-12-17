package com.example.ecommerce.E_Commerce.repository;

import com.example.ecommerce.E_Commerce.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
