package com.gabriel.sendgift.core.domain.user.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;
    private String cep;
}
