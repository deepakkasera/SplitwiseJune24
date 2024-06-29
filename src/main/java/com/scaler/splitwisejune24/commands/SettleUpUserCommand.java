package com.scaler.splitwisejune24.commands;

import com.scaler.splitwisejune24.controllers.SettleUpController;
import com.scaler.splitwisejune24.dtos.SettleUpUserRequestDto;
import com.scaler.splitwisejune24.dtos.SettleUpUserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SettleUpUserCommand implements Command {
    private SettleUpController settleUpController;

    public SettleUpUserCommand(SettleUpController settleUpController) {
        this.settleUpController = settleUpController;
    }

    @Override
    public boolean matches(String input) {
        //input - 1234 SettleUp
        // words -> [1234, SettleUp]
        List<String> words = List.of(input.split(" "));

        return words.size() == 2 && words.get(1).equals(CommandKeywords.settleUpCommand);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));

        Long userId = Long.valueOf(words.get(0));

        SettleUpUserRequestDto requestDto = new SettleUpUserRequestDto();
        requestDto.setUserId(userId);

        SettleUpUserResponseDto responseDto = settleUpController.settleUpUser(requestDto);
    }
}
