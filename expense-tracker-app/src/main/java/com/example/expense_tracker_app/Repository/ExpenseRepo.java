package com.example.expense_tracker_app.Repository;

import com.example.expense_tracker_app.Entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense,Long> {
}
