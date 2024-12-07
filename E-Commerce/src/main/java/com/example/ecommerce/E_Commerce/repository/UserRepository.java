package com.example.ecommerce.E_Commerce.repository;

import com.example.ecommerce.E_Commerce.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
     Optional<User> findByUserName(String name);

     boolean existsByUserName(String username);

     boolean existsByEmail(String email);
}
