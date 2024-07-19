package com.shiv.ExpenseTracker.Repository;

import org.springframework.stereotype.Repository;

import com.shiv.ExpenseTracker.Entity.Expense;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long>{

}
