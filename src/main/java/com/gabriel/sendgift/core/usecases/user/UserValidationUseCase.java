package com.gabriel.sendgift.core.usecases.user;

import com.gabriel.sendgift.core.domain.user.User;

public interface UserValidationUseCase {
    void validate(User user);
}
