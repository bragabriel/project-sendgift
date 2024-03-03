package com.gabriel.sendgift.application.controllers;

import com.gabriel.sendgift.application.services.UserServiceImpl;
import com.gabriel.sendgift.core.domain.user.dto.UserDto;
import com.gabriel.sendgift.core.domain.user.dto.UserResponse;
import com.gabriel.sendgift.core.domain.user.dto.UserUpdateDto;
import com.gabriel.sendgift.core.domain.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> users = userServiceImpl.getAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") String id){
        UserResponse userResponse = userServiceImpl.getById(id);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping()
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserDto userDto) {
        User user = User.mapToUser(userDto);
        UserResponse userResponse = userServiceImpl.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") String id, @RequestBody UserUpdateDto userUpdateDto){
        UserResponse updatedUser = userServiceImpl.updateUser(id, userUpdateDto);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") String id){
        userServiceImpl.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
