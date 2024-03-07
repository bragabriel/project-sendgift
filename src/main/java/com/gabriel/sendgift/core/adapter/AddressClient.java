package com.gabriel.sendgift.core.adapter;

import com.gabriel.sendgift.core.domain.address.dto.AddressExternalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cep-microservice", url = "http://localhost:8081/api/address")
public interface AddressClient {

    @GetMapping("/{cep}")
    AddressExternalResponse getAddressByCep(@PathVariable("cep") String cep);
}