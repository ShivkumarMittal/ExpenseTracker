package com.shiv.ExpenseTracker.Services.expense;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.shiv.ExpenseTracker.Entity.Expense;
import com.shiv.ExpenseTracker.Repository.ExpenseRepository;
import com.shiv.ExpenseTracker.dto.ExpenseDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    
    private final ExpenseRepository expenseRepository;

    public Expense postExpense(ExpenseDTO expenseDTO)
    {
        return saveOrUpdateExpense(new Expense(), expenseDTO);

    }



    private Expense saveOrUpdateExpense(Expense expense , ExpenseDTO expenseDTO)
    {
        expense.setTitle(expenseDTO.getTitle());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());

        return expenseRepository.save(expense);


    }

    public Expense updatExpense(Long id , ExpenseDTO expenseDTO)
    {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent())
        {
            return saveOrUpdateExpense(optionalExpense.get(), expenseDTO);
        }
        else{
            throw new EntityNotFoundException("Expense not present with id "+id);
        }
        

    }

    public List<Expense> getAllExpense()
    {
        return expenseRepository.findAll().stream()
        .sorted(Comparator.comparing(Expense::getDate).reversed())
        .collect(Collectors.toList());
    }

    public Expense getExpenseById(Long id)
    {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if(optionalExpense.isPresent())
        {
            return optionalExpense.get();
        }
        else{
            throw new EntityNotFoundException("Expense not present with id"+id);
        }
    }

   
    
}
