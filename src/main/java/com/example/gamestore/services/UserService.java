package com.example.gamestore.services;

import com.example.gamestore.entities.users.LoginDTO;
import com.example.gamestore.entities.users.RegistrationDTO;
import com.example.gamestore.entities.users.User;

public interface UserService {

    User register(RegistrationDTO registrationDTO);

    void login(LoginDTO loginDTO);

    void logout();

}
