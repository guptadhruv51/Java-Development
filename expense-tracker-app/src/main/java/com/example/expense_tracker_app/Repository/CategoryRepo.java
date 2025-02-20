package com.example.expense_tracker_app.Repository;

import com.example.expense_tracker_app.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long>
{


}
