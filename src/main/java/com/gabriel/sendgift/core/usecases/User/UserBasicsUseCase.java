package com.gabriel.sendgift.core.usecases.User;

import com.gabriel.sendgift.core.domain.user.User;
import com.gabriel.sendgift.core.domain.user.dto.UserUpdateDto;

import java.util.List;

public interface UserBasicsUseCase {

    List<User> getAll();
    User getById(String id);
    User registerUser(User user);
    User updateUser(String id, UserUpdateDto userUpdateDto);
    void deleteUser(String id);
}
