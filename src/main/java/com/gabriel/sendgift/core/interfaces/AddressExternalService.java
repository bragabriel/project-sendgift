package com.gabriel.sendgift.core.interfaces;

import com.gabriel.sendgift.core.domain.dto.AddressExternalResponse;

public interface AddressExternalService {
    AddressExternalResponse getAddressByCep(String cep);
}
