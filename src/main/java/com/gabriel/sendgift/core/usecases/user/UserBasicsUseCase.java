package com.gabriel.sendgift.core.usecases.user;

import com.gabriel.sendgift.core.domain.user.User;
import com.gabriel.sendgift.core.domain.user.dto.UserResponse;
import com.gabriel.sendgift.core.domain.user.dto.UserUpdateDto;

import java.util.List;

public interface UserBasicsUseCase {

    List<UserResponse> getAll();
    UserResponse getById(String id);
    UserResponse getById(String id, String message);
    UserResponse registerUser(User user);
    UserResponse updateUser(String id, UserUpdateDto userUpdateDto);
    void deleteUser(String id);
}
