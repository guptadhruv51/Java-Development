package com.example.ecommerce.E_Commerce.repository;

import com.example.ecommerce.E_Commerce.Models.Payment;
import com.example.ecommerce.E_Commerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment,Long>
{

}
