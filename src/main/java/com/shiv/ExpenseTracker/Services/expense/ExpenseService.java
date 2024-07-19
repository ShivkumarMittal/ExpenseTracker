package com.shiv.ExpenseTracker.Services.expense;

import java.util.List;

import com.shiv.ExpenseTracker.Entity.Expense;
import com.shiv.ExpenseTracker.dto.ExpenseDTO;

public interface ExpenseService {
    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpense();

    Expense getExpenseById(Long id);

    Expense updatExpense(Long id , ExpenseDTO expenseDTO);

    void deleteExpense(Long id);

}
