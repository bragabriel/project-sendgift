package com.gabriel.sendgift.core.domain.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String cep;
    private String street;
    private String neighborhood;
    private String number;
}
