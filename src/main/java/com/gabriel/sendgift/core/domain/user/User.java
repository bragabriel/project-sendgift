package com.gabriel.sendgift.core.domain.user;

import com.gabriel.sendgift.core.domain.user.dto.UserDto;
import com.gabriel.sendgift.core.domain.address.Address;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Address address;

    public static User mapToUser(UserDto userDto) {
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