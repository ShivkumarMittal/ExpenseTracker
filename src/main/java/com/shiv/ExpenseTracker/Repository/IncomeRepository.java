package com.shiv.ExpenseTracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiv.ExpenseTracker.Entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Long> {

}
