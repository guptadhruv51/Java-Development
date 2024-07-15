package com.example.minorproject1.Controllers;

import com.example.minorproject1.models.Student;
import com.example.minorproject1.models.TransactionType;
import com.example.minorproject1.models.Transactions;
import com.example.minorproject1.models.User;
import com.example.minorproject1.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public String initiateTransaction(
                                      @RequestParam("bookId") Integer bookId,
                                      @RequestParam("TransactionType") TransactionType transactionType) throws Exception {

        SecurityContext securityContext= SecurityContextHolder.getContext();
        Authentication authentication= securityContext.getAuthentication();
        User user= (User) authentication.getPrincipal();
        Student student=user.getStudent();
        Integer studentId=null;
        if(student!=null)
        {
            studentId=student.getId();
        }
        else {
            throw new Exception("User is not a student");
        }

            return transactionService.initiateTransaction(studentId,bookId,transactionType);
    }

    @GetMapping("/admin/{bookID}")
    public List<Transactions> getTransactions(@PathVariable("bookID") Integer bookID)
    {
        try {
            this.transactionService.getTransactionList(bookID);
        }
        catch (Exception e)
        {

            return new ArrayList<Transactions>();
        }
        return null;
    }
    /**
     * Add APIS
     * getTransaction by id (paginated)
     * getTransactionStudentId
     * getTransactionBookId
     */
}
