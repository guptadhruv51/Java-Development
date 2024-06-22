package com.example.minorproject1.service;

import com.example.minorproject1.models.TransactionType;
import org.springframework.stereotype.Service;

@Service
public class TransactionService
{
    public String initiateTransaction(Integer studentId, Integer bookId,
                                      TransactionType transactionType) throws Exception {
        switch(transactionType)
        {
            case Issue:
                return initiateIssue(studentId,bookId);
            case Return:
                return initiateReturn(studentId,bookId);
            default:
                throw new Exception("Invalid Transaction Type");
        }
    }
private String initiateIssue(Integer StudentId, Integer bookId)
{
    /**
     * Logic:
     * ---------------------------Validations need to be done at service layer --------------------------------------
     *  Validate a student and book, throw 400/404 if any of these details are invalid.
     *  Validate whether the book is available or not
     *  Validate student's limit of issuance of number of books
     *  ----------------------------------------------------------------------------
     *  Create transaction entry in the transaction table with status a Pending
     *
        *  Make the book unavailable so that no one can issue the book concurrently /assign it to the student (book.student=?)
        *  Update the transaction entry with the status as success
     *  If any issue in the last two-steps update transaction entry with failed
      */
    
}
private String initiateReturn(Integer StudentId, Integer bookId)
{

}
}
