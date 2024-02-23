package com.gabriel.sendgift.core.domain.entity;

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
    private String uf;
    private String localidade;
    private String complemento;
    private String bairro;
    private String logradouro;
}
