package com.gabriel.sendgift.core.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Address {
    private String cep;
    private String street;
    private String neighborhood;
    private String number;
}
