package com.shiv.ExpenseTracker.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiv.ExpenseTracker.Entity.Expense;
import com.shiv.ExpenseTracker.Services.expense.ExpenseService;
import com.shiv.ExpenseTracker.dto.ExpenseDTO;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDTO dto)
    {
        Expense createdExpense = expenseService.postExpense(dto);
        if(createdExpense!=null)
        {
            return ResponseEntity.status(
                HttpStatus.CREATED
            ).body(createdExpense);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllResponseEntity()
    {
        return ResponseEntity.ok(expenseService.getAllExpense());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id)
    {
        try{
            return ResponseEntity.ok(expenseService.getExpenseById(id));
        }
        catch(EntityNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");

        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id , @RequestBody ExpenseDTO dto)
    {
        try{
            return ResponseEntity.ok(expenseService.updatExpense(id, dto));
        }
        catch(EntityNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");

        }
        

        

    }
}
