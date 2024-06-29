package com.scaler.splitwisejune24.services;

import com.scaler.splitwisejune24.dtos.SettleUpGroupRequestDto;
import com.scaler.splitwisejune24.models.*;
import com.scaler.splitwisejune24.repositories.ExpenseRepository;
import com.scaler.splitwisejune24.repositories.ExpenseUserRepository;
import com.scaler.splitwisejune24.repositories.GroupRepository;
import com.scaler.splitwisejune24.repositories.UserRepository;
import com.scaler.splitwisejune24.strategy.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SettleUpService {
    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;
    private SettleUpStrategy settleUpStrategy;
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;

    public SettleUpService(UserRepository userRepository,
                           ExpenseUserRepository expenseUserRepository,
                           SettleUpStrategy settleUpStrategy,
                           GroupRepository groupRepository,
                           ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> settleUpUser(Long userId) {
        /*
        1. Get the user with the given userId.
        2. Get all the expenses for this user.
        3. Iterate through all the expenses, and find out total extra/lesser amount
        paid by every user involved in the expenses.
        4. Implement Min/Max Heap algorithm to settle up users.
         */

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Invalid userId - " + userId);
        }

        User user = optionalUser.get();

        List<ExpenseUser> expenseUsers = expenseUserRepository.findAllByUser(user);

        Set<Expense> expenses = new HashSet<>();

        for (ExpenseUser expenseUser : expenseUsers) {
            expenses.add(expenseUser.getExpense());
        }

        //Settle Up the expenses.
        List<Expense> settleUpExpenses = settleUpStrategy.settleUp(expenses.stream().toList());
        /*
        A -> B : 200
        A -> C : 700
        D -> C : 500
         */

        List<Expense> expensesToReturn = new ArrayList<>();

        for (Expense expense : settleUpExpenses) {
            for (ExpenseUser expenseUser : expense.getExpenseUsers()) {
                if (expenseUser.getUser().equals(user)) {
                    expensesToReturn.add(expense);
                    break;
                }
            }
        }

        return expensesToReturn;
    }

    public List<Expense> settleUpGroup(Long groupId) {

        Optional<Group> optionalGroup = groupRepository.findById(groupId);

        if (optionalGroup.isEmpty()) {
            throw new RuntimeException("Invalid group id : " + groupId);
        }

        //Find all the expenses for this group.
        //select * from expenses where group_id = 123.
        List<Expense> expenses = expenseRepository.findAllByGroup(optionalGroup.get());

        //Settle Up Algorithm
        return settleUpStrategy.settleUp(expenses);
    }
}


/*
User - Krithi (1234)

Expense1 - Coffee => multiple ExpenseUsers
Expense2 - Dinner
Expense3 - Lunch
Expense4 - Party



User - Krithi

Coffee, Kriti, 500, PAID_BY
Coffee, Kriti, 200, HAD_TO_PAY
Dinner, Krithi, 1000, HAD_TO_PAY


Coffee -> Kriti, 500, PAID_BY.
       -> Sasi, 300, PAID_BY
       -> Deepak, 200, HAD_TO_PAY
       -> Hitesh, 200, HAD_TO_PAY
 */
