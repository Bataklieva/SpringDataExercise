package com.example.gamestore;

import com.example.gamestore.entities.ValidationException;
import com.example.gamestore.entities.users.LoginDTO;
import com.example.gamestore.entities.users.RegistrationDTO;
import com.example.gamestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Runner implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public Runner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner sc = new Scanner(System.in);

        String[] input = sc.next().split("\\|");

        try {
            execute(input);

        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println();

    }

    private void execute(String[] input) {

        if (input[0].equals("RegisterUser")) {
            RegistrationDTO registrationDTO = new RegistrationDTO(input[1], input[2], input[3], input[4]);
            userService.register(registrationDTO);
        }

        if (input[0].equals("LoginUser")) {
            LoginDTO loginDTO = new LoginDTO(input[1], input[2]);
            userService.login(loginDTO);

        }
    }
}
