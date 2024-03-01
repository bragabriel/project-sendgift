package com.gabriel.sendgift.core.usecases.User;

import com.gabriel.sendgift.application.exceptions.EmailAlreadyExistsException;
import com.gabriel.sendgift.core.domain.user.User;
import com.gabriel.sendgift.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserEmailValidationUseCase implements UserValidationUseCase {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("O e-mail já está cadastrado.");
        }
    }
}