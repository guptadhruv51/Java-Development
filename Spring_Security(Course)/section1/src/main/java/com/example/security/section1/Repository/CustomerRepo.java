package com.example.security.section1.Repository;

import com.example.security.section1.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long>
{


    Optional<Customer> findByEmail(String Email);


}
