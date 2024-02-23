package com.gabriel.sendgift.core.adapter;

import com.gabriel.sendgift.core.domain.dto.AddressExternalResponse;
import com.gabriel.sendgift.core.interfaces.AddressExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCEPAdapter implements AddressExternalService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/";

    @Override
    public AddressExternalResponse getAddressByCep(String cep) {
        String url = VIA_CEP_URL + cep + "/json";
        return restTemplate.getForObject(url, AddressExternalResponse.class);
    }
}