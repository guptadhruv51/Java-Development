package com.example.minorproject1.repository;

import com.example.minorproject1.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions,Integer>
{

     Transactions findTopByStudentAndBookAndTransactionTypeAndTransactionStatusOrderByIdDesc
             (Student student, Book book, TransactionType transactionType, TransactionStatus transactionStatus);

     @Override
     List<Transactions> findAllById(Iterable<Integer> integers);
}
