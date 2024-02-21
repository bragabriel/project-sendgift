package com.gabriel.sendgift.core.domain.dto;

import lombok.Data;

@Data
public class UserWithAddressDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private AddressDto address;
}
