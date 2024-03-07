package com.gabriel.sendgift.core.domain.address.dto;

import com.gabriel.sendgift.core.domain.address.Address;
import lombok.Data;

@Data
public class AddressExternalResponse {
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;

    public static Address mapToAddress(AddressExternalResponse addressExternalResponse) {
        Address address = new Address();
        address.setCep(addressExternalResponse.getCep());
        address.setLogradouro(addressExternalResponse.getLogradouro());
        address.setBairro(addressExternalResponse.getBairro());
        address.setCidade(addressExternalResponse.getCidade());
        address.setEstado(addressExternalResponse.getEstado());
        return address;
    }
}
