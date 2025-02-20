package com.example.expense_tracker_app.Service;

import com.example.expense_tracker_app.DTO.ExpenseDTO;

import java.util.List;

public interface ExpenseService
{
    ExpenseDTO createExpense(ExpenseDTO expenseDTO);


    ExpenseDTO getExpenseByID(Long expenseID);

    List<ExpenseDTO> getAllExpenses();
}
