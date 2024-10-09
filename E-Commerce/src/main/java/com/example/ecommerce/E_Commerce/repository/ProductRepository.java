package com.example.ecommerce.E_Commerce.repository;

import com.example.ecommerce.E_Commerce.Models.Category;
import com.example.ecommerce.E_Commerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{



    List<Product> findByCategoryOrderByPriceAsc(Category category);

    Collection<Object> findByProductNameLikeIgnoreCase(String keyword);
}
