package com.example.expense_tracker_app.Service;

import com.example.expense_tracker_app.DTO.ExpenseDTO;
import com.example.expense_tracker_app.Entity.Category;
import com.example.expense_tracker_app.Entity.Expense;
import com.example.expense_tracker_app.Mapper.ExpenseMapper;
import com.example.expense_tracker_app.Repository.CategoryRepo;
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
    @Autowired
    CategoryRepo categoryRepo;


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

    @Override
    public ExpenseDTO updateExpense(Long expenseID, ExpenseDTO expenseDTO) {

        Expense expense=expenseRepo.findById(expenseID)
                .orElseThrow(()->new RuntimeException("Id not found "+expenseID));

        expense.setAmount(expenseDTO.amount());
        expense.setLocalDate(expenseDTO.localDate());
        if(expenseDTO.categoryDTO()!=null)
        {
            Category category=categoryRepo.findById(expenseDTO.categoryDTO().id())
                    .orElseThrow(()->new RuntimeException("Category not found "));
            expense.setCategory(category);
        }
        Expense savedExpense=expenseRepo.save(expense);
        return ExpenseMapper.mapToexpenseDTO(savedExpense);
    }

    @Override
    public void deleteExpense(Long expenseID) {
        Expense expense=expenseRepo.findById(expenseID)
                .orElseThrow(()-> new RuntimeException("Expense not found "+expenseID));
        expenseRepo.delete(expense);
    }
}
