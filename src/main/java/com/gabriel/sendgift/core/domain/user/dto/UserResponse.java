package com.gabriel.sendgift.core.domain.user.dto;

import com.gabriel.sendgift.core.domain.address.Address;
import com.gabriel.sendgift.core.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String name;
    private String email;
    private Address address;

    public UserResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.address = user.getAddress();
    }

    public static UserResponse mapToUserResponse(User user){
        UserResponse userResponse = new UserResponse();

        userResponse.id = user.getId();
        userResponse.name = user.getName();
        userResponse.email = user.getEmail();
        userResponse.address = user.getAddress();

        return userResponse;
    }
}
