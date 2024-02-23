package com.gabriel.sendgift.core.domain.dto;

import com.gabriel.sendgift.core.domain.entity.Address;
import lombok.Data;

@Data
public class AddressExternalResponse {
    private String cep;
    private String uf;
    private String localidade;
    private String complemento;
    private String bairro;
    private String logradouro;
    private String gia;
    private String ibge;
    private String ddd;
    private String siafi;

    public static Address mapToAddress(AddressExternalResponse addressExternalResponse) {
        Address address = new Address();
        address.setCep(addressExternalResponse.getCep());
        address.setUf(addressExternalResponse.getUf());
        address.setLocalidade(addressExternalResponse.getLocalidade());
        address.setComplemento(addressExternalResponse.getComplemento());
        address.setBairro(addressExternalResponse.getBairro());
        address.setLogradouro(addressExternalResponse.getLogradouro());
        return address;
    }
}
