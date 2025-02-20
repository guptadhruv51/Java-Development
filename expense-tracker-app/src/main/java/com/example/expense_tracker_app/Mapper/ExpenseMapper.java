package com.example.expense_tracker_app.Mapper;

import com.example.expense_tracker_app.DTO.CategoryDTO;
import com.example.expense_tracker_app.DTO.ExpenseDTO;
import com.example.expense_tracker_app.Entity.Category;
import com.example.expense_tracker_app.Entity.Expense;

public class ExpenseMapper
{
    public static Expense mapToExpense(ExpenseDTO expenseDTO)
    {
        Category category=new Category();
        category.setId(expenseDTO.categoryDTO().id());
        return new Expense(
                expenseDTO.id(),
                expenseDTO.amount(),
                expenseDTO.localDate(),
                category
        );
    }

    // Map Category entity to CategoryDto
    public static ExpenseDTO mapToexpenseDTO(Expense expense){
        return new ExpenseDTO(
                expense.getId(),
                expense.getAmount(),
                expense.getLocalDate(),
                new CategoryDTO(
                        expense.getCategory().getId(),
                        expense.getCategory().getName()
                )
        );
    }
}
