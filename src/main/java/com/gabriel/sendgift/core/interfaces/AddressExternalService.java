package com.gabriel.sendgift.core.interfaces;

import com.gabriel.sendgift.core.domain.entity.Address;


public interface AddressExternalService {
    Address getAddressByCep(String cep);
}
