package com.scaler.splitwisejune24.services;

import com.scaler.splitwisejune24.dtos.SettleUpGroupRequestDto;
import com.scaler.splitwisejune24.models.Expense;
import com.scaler.splitwisejune24.models.ExpenseType;
import com.scaler.splitwisejune24.models.ExpenseUser;
import com.scaler.splitwisejune24.models.User;
import com.scaler.splitwisejune24.repositories.ExpenseUserRepository;
import com.scaler.splitwisejune24.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SettleUpService {
    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;

    public SettleUpService(UserRepository userRepository,
                           ExpenseUserRepository expenseUserRepository) {
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
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


    }

    public List<Expense> settleUpGroup(Long groupId) {
        return null;
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
