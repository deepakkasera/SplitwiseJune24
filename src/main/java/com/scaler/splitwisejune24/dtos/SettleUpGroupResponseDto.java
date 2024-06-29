package com.scaler.splitwisejune24.dtos;

import com.scaler.splitwisejune24.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpGroupResponseDto {
    private List<Expense> expenses;
}
