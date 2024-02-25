package com.gabriel.sendgift.application.controllers;

import com.gabriel.sendgift.application.services.UserService;
import com.gabriel.sendgift.core.domain.user.dto.UserDto;
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
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable("id") String id){
        User user = userService.getById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping()
    public ResponseEntity<User> registerUser(@RequestBody UserDto userDto) {
        User user = User.mapToUser(userDto);
        var response = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") String id, @RequestBody UserUpdateDto userUpdateDto){
        User updatedUser = userService.update(id, userUpdateDto);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
