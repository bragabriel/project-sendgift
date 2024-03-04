package com.gabriel.sendgift.core.usecases.address;

import com.gabriel.sendgift.core.domain.address.dto.AddressExternalResponse;

public interface AddressExternalServiceUseCase {
    AddressExternalResponse getAddressByCep(String cep);
}
