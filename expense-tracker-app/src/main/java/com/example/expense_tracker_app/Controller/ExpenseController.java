package com.example.expense_tracker_app.Controller;

import com.example.expense_tracker_app.DTO.ExpenseDTO;
import com.example.expense_tracker_app.Service.ExpenseService;
import com.example.expense_tracker_app.Service.ExpenseServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController
{
    @Autowired
    ExpenseServiceIMPL expenseService;

    @PostMapping("/add")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody ExpenseDTO expenseDTO)
    {
       ExpenseDTO savedExpense= expenseService.createExpense(expenseDTO);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }

    @GetMapping("/get/{expenseID}")
    public ResponseEntity<ExpenseDTO> getExpensebyID( @PathVariable("expenseID") Long expenseID)
    {
        ExpenseDTO expenseDTO=expenseService.getExpenseByID(expenseID);
        return new ResponseEntity<>(expenseDTO,HttpStatus.FOUND);
    }

    @GetMapping("/get/All")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses()
    {
      List<ExpenseDTO> expenseDTOS=  expenseService.getAllExpenses();
      return new ResponseEntity<>(expenseDTOS,HttpStatus.FOUND);
    }
}
