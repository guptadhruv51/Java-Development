package com.example.expense_tracker_app.Entity;

import com.example.expense_tracker_app.DTO.CategoryDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "expenses")
public class Expense
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDate localDate;

    @ManyToOne
    @JoinColumn(name="categoryID",nullable = false) //foreign key
    private Category category;

    public Expense(Long id, BigDecimal amount, LocalDate localDate, Category category) {
        this.id = id;
        this.amount = amount;
        this.localDate = localDate;
        this.category = category;
    }
    public Expense()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
