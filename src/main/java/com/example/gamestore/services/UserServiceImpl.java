package com.example.gamestore.services;

import com.example.gamestore.entities.ValidationException;
import com.example.gamestore.entities.users.LoginDTO;
import com.example.gamestore.entities.users.RegistrationDTO;
import com.example.gamestore.entities.users.User;
import com.example.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegistrationDTO registrationDTO) {

        ModelMapper mapper = new ModelMapper();
        User toRegister = mapper.map(registrationDTO, User.class);

        long userCount = this.userRepository.count();

        if (userCount == 0) {
            toRegister.setAdmin(true);
        }

        return this.userRepository.save(toRegister);
    }

    @Override
    public void login(LoginDTO loginDTO) {
        Optional<User> byEmailAndPassword =
                userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if (byEmailAndPassword.isEmpty()) {
            throw new ValidationException("Incorrect email / password");
        }

        System.out.printf("Successfully logged in %s%n", byEmailAndPassword.get().getFullName());

    }

    @Override
    public void logout() {


    }
}
