package com.example.ecommerce.E_Commerce.repository;

import com.example.ecommerce.E_Commerce.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
        Category findByCategoryName(String categoryName);
}
