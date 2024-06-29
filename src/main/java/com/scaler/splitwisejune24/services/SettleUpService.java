package com.scaler.splitwisejune24.services;

import com.scaler.splitwisejune24.dtos.SettleUpGroupRequestDto;
import com.scaler.splitwisejune24.models.Expense;
import com.scaler.splitwisejune24.models.ExpenseType;
import com.scaler.splitwisejune24.models.User;
import com.scaler.splitwisejune24.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {
    private UserRepository userRepository;

    public SettleUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
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


    }

    public List<Expense> settleUpGroup(Long groupId) {
        return null;
    }
}
