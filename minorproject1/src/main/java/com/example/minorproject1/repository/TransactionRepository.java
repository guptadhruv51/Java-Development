package com.example.minorproject1.repository;

import com.example.minorproject1.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions,Integer>
{

}
