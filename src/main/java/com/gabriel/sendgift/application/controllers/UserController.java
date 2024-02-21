package com.gabriel.sendgift.application.controllers;

import com.gabriel.sendgift.core.domain.dto.UserDto;
import com.gabriel.sendgift.core.domain.entity.Address;
import com.gabriel.sendgift.core.domain.entity.User;
import com.gabriel.sendgift.core.usecases.User.UserRegistrationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRegistrationUseCase userRegistrationUseCase;

    public UserController(UserRegistrationUseCase userRegistrationUseCase) {
        this.userRegistrationUseCase = userRegistrationUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User user = mapUserDtoToUser(userDto);
        var response = userRegistrationUseCase.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        Address address = new Address();
        address.setCep(userDto.getCep());
        user.setAddress(address);

        return user;
    }
}
