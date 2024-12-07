package com.example.ecommerce.E_Commerce.repository;

import com.example.ecommerce.E_Commerce.Models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleRepository,Integer> 
{

    Optional<Object> findByRoleName(AppRole appRole);
}
