package com.gabriel.sendgift.application.services;

import com.gabriel.sendgift.application.exceptions.UserNotFoundException;
import com.gabriel.sendgift.core.domain.address.dto.AddressExternalResponse;
import com.gabriel.sendgift.core.domain.user.dto.UserUpdateDto;
import com.gabriel.sendgift.core.interfaces.AddressExternalService;
import com.gabriel.sendgift.core.domain.address.Address;
import com.gabriel.sendgift.core.domain.user.User;
import com.gabriel.sendgift.core.repositories.UserRepository;
import com.gabriel.sendgift.core.usecases.User.UserBasicsUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserBasicsUseCase {

    private final UserRepository userRepository;
    private final AddressExternalService addressService;

    public UserService(
            AddressExternalService addressService,
            UserRepository userRepository
    ) {
        this.addressService = addressService;
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty())
            throw new UserNotFoundException("Nenhum usuário cadastrado");

        return users;
    }

    @Override
    public User getById(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado para o ID: " + id));

        return user;
    }

    @Override
    public User getById(String id, String errorMessage) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(errorMessage));
        return null;
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

    @Override
    public User updateUser(String id, UserUpdateDto userUpdateDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado para o ID: " + id));

        if(!userUpdateDto.getName().isEmpty()) user.setName(userUpdateDto.getName());

        if(!userUpdateDto.getCep().isEmpty()){
            AddressExternalResponse addressExternalResponse = addressService.getAddressByCep(userUpdateDto.getCep());
            Address address = AddressExternalResponse.mapToAddress(addressExternalResponse);
            user.setAddress(address);
        }

        userRepository.save(user);

        return user;
    }

    @Override
    public void deleteUser(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado para o ID: " + id));

        userRepository.delete(user);
    }
}

