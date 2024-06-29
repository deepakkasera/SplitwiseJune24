package com.scaler.splitwisejune24.repositories;

import com.scaler.splitwisejune24.models.Expense;
import com.scaler.splitwisejune24.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByGroup(Group group);
}
