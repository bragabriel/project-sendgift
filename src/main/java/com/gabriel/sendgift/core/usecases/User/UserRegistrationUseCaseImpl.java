package com.gabriel.sendgift.core.usecases.User;

import com.gabriel.sendgift.core.interfaces.AddressExternalService;
import com.gabriel.sendgift.core.domain.entity.Address;
import com.gabriel.sendgift.core.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationUseCaseImpl implements UserRegistrationUseCase {
    private final AddressExternalService addressService;

    public UserRegistrationUseCaseImpl(AddressExternalService addressService) {
        this.addressService = addressService;
    }

    @Override
    public User registerUser(User user) {
        // TODO: Validar dados usuário

        Address address = addressService.getAddressByCep(user.getAddress().getCep());

        user.setAddress(address);

        // TODO: Registro do usuário no banco

        return user;
    }
}

