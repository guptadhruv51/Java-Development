package com.example.minorproject1.Controllers;

import com.example.minorproject1.models.TransactionType;
import com.example.minorproject1.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController
{
    /**
     * Way 1:
     * Separate APIs for issue and returns
     *  Issue: Student id + bookId
     *  Return: Student id+ bookId
     *
     *  Way 2:
     *  Single Api for transaction (initiate)
     *      Student id+bookId+ type of transaction
     */
    @Autowired
    TransactionService transactionService;
    @PostMapping("/initiate")
    public String initiateTransaction(@RequestParam("studentId") Integer StudentId,
                                      @RequestParam("bookId") Integer bookId,
                                      @RequestParam("TransactionType") TransactionType transactionType) throws Exception {

            return transactionService.initiateTransaction(StudentId,bookId,transactionType);
    }
}
