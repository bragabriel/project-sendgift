package com.gabriel.sendgift.application.controllers;

import com.gabriel.sendgift.application.services.UserService;
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

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(){
        List<UserResponse> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable("id") String id){
        UserResponse userResponse = userService.getById(id);
        return ResponseEntity.ok().body(userResponse);
    }

    @PostMapping()
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserDto userDto) {
        User user = User.mapToUser(userDto);
        UserResponse userResponse = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable("id") String id, @RequestBody UserUpdateDto userUpdateDto){
        UserResponse updatedUser = userService.updateUser(id, userUpdateDto);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") String id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
