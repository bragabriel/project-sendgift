package com.gabriel.sendgift.application.services;

import com.gabriel.sendgift.application.exceptions.UserNotFoundException;
import com.gabriel.sendgift.core.domain.address.dto.AddressExternalResponse;
import com.gabriel.sendgift.core.domain.user.dto.UserUpdateDto;
import com.gabriel.sendgift.core.interfaces.AddressExternalService;
import com.gabriel.sendgift.core.domain.address.Address;
import com.gabriel.sendgift.core.domain.user.User;
import com.gabriel.sendgift.core.repositories.UserRepository;
import com.gabriel.sendgift.core.usecases.User.UserRegistrationUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserRegistrationUseCase {

    private final UserRepository userRepository;
    private final AddressExternalService addressService;

    public UserService(
            AddressExternalService addressService,
            UserRepository userRepository
    ) {
        this.addressService = addressService;
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(String id){

        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return user;
    }

    @Override
    public User registerUser(User user) {
        // TODO: Validar dados usuário

        AddressExternalResponse addressExternalResponse = addressService.getAddressByCep(user.getAddress().getCep());
        Address address = AddressExternalResponse.mapToAddress(addressExternalResponse);

        user.setAddress(address);

        userRepository.save(user);

        return user;
    }

    public User update(String id, UserUpdateDto userUpdateDto){
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        if(!userUpdateDto.getName().isEmpty()) user.setName(userUpdateDto.getName());

        if(!userUpdateDto.getCep().isEmpty()){
            AddressExternalResponse addressExternalResponse = addressService.getAddressByCep(userUpdateDto.getCep());
            Address address = AddressExternalResponse.mapToAddress(addressExternalResponse);
            user.setAddress(address);
        }

        userRepository.save(user);

        return user;
    }

    public void delete(String id){
        User category = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userRepository.delete(category);
    }
}

