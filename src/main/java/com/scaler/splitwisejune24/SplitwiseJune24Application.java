package com.scaler.splitwisejune24;

import com.scaler.splitwisejune24.commands.CommandExecutor;
import com.scaler.splitwisejune24.commands.SettleUpUserCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SplitwiseJune24Application implements CommandLineRunner  {
    private CommandExecutor commandExecutor;

    public SplitwiseJune24Application(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseJune24Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.next();
            commandExecutor.execute(input);
        }

    }
}
