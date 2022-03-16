package com.example.gamestore.entities.users;

import com.example.gamestore.entities.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationDTO {

    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public RegistrationDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;

        this.validate();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    private void validate() {

        String passRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passRegex);
        Matcher matcher = pattern.matcher(password);

        if (!email.contains("@") || !email.contains(".")) {
            throw new ValidationException("Email must contain @ and .");
        }

        if (!matcher.matches()) {
            throw new ValidationException("Password must contain at least 1 uppercase, 1 lowercase " +
                    "letter and 1 digit.%nMust be at least 6 symbols long.");
        }

        if (!confirmPassword.equals(password)) {
            throw new ValidationException("Passwords must equals.");
        }

    }


}
