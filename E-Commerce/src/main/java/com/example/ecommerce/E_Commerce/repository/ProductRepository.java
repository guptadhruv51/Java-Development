package com.example.ecommerce.E_Commerce.repository;

import com.example.ecommerce.E_Commerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{

}
