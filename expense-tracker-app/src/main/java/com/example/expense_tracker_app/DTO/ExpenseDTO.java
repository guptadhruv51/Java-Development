package com.example.expense_tracker_app.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseDTO(Long id,
                         BigDecimal amount,
                         LocalDate localDate,
                         CategoryDTO categoryDTO )
{
}
