package com.gabriel.sendgift.core.usecases.User;

import com.gabriel.sendgift.application.exceptions.InvalidPasswordException;
import com.gabriel.sendgift.core.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordValidationUseCase implements UserValidationUseCase {

    @Override
    public void validate(User user) {
        String password = user.getPassword();

        if (password.length() < 8) {
            throw new InvalidPasswordException("A senha deve conter pelo menos 8 caracteres.");
        }

        if (!password.matches("^(?=.*[A-Z])(?=.*[a-z]).*$")) {
            throw new InvalidPasswordException("A senha deve conter pelo menos uma letra maiúscula e uma letra minúscula.");
        }

        if (!password.matches(".*\\d.*")) {
            throw new InvalidPasswordException("A senha deve conter pelo menos um número.");
        }

        if (!password.matches(".*[!@#$%^&*()-+=`~\\[\\]{}|;:'\",<.>/?].*")) {
            throw new InvalidPasswordException("A senha deve conter pelo menos um caractere especial.");
        }
    }
}