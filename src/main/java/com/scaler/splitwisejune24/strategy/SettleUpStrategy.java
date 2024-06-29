package com.scaler.splitwisejune24.strategy;

import com.scaler.splitwisejune24.models.Expense;

import java.util.List;

public interface SettleUpStrategy {
    List<Expense> settleUp(List<Expense> expenses);
}

//DUMMY Expense == Transaction
