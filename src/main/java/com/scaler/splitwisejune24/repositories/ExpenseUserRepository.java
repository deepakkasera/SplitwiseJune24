package com.scaler.splitwisejune24.repositories;

import com.scaler.splitwisejune24.models.ExpenseUser;
import com.scaler.splitwisejune24.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseUserRepository extends JpaRepository<ExpenseUser, Long> {
    List<ExpenseUser> findAllByUser(User user);
}
