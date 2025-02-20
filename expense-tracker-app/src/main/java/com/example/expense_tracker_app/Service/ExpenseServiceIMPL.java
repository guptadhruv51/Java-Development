package com.example.expense_tracker_app.Service;

import com.example.expense_tracker_app.DTO.ExpenseDTO;
import com.example.expense_tracker_app.Entity.Expense;
import com.example.expense_tracker_app.Mapper.ExpenseMapper;
import com.example.expense_tracker_app.Repository.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceIMPL implements ExpenseService
{

    @Autowired
    ExpenseRepo expenseRepo;


    @Override
    public ExpenseDTO createExpense(ExpenseDTO expenseDTO)
    {
        Expense expense= ExpenseMapper.mapToExpense(expenseDTO);
        Expense savedExpense=expenseRepo.save(expense);
        return ExpenseMapper.mapToexpenseDTO(expense);
    }

    @Override
    public ExpenseDTO getExpenseByID(Long expenseID) {
        Expense expense=expenseRepo.findById(expenseID).orElseThrow(()->new RuntimeException("No expenses found for given id"));
        return ExpenseMapper.mapToexpenseDTO(expense);
    }

    @Override
    public List<ExpenseDTO> getAllExpenses() {
       List<Expense>expenses= expenseRepo.findAll();
       return expenses.stream().map((expense -> ExpenseMapper.mapToexpenseDTO(expense))).collect(Collectors.toList());
    }
}
