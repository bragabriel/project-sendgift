package com.gabriel.sendgift.application.services.user;

import com.gabriel.sendgift.application.exceptions.UserNotFoundException;
import com.gabriel.sendgift.core.adapter.AddressClient;
import com.gabriel.sendgift.core.domain.address.dto.AddressExternalResponse;
import com.gabriel.sendgift.core.domain.user.dto.UserResponse;
import com.gabriel.sendgift.core.domain.user.dto.UserUpdateDto;
import com.gabriel.sendgift.core.domain.address.Address;
import com.gabriel.sendgift.core.domain.user.User;
import com.gabriel.sendgift.core.repositories.UserRepository;
import com.gabriel.sendgift.core.usecases.user.UserBasicsUseCase;
import com.gabriel.sendgift.core.usecases.user.UserValidationUseCase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserBasicsUseCase{

    private final UserRepository userRepository;
    public final AddressClient addressClient;
    private List<UserValidationUseCase> validationUseCases;

    public UserServiceImpl(
            AddressClient addressClient,
            UserRepository userRepository,
            List<UserValidationUseCase> validationUseCase
    ) {
        this.addressClient = addressClient;
        this.userRepository = userRepository;
        this.validationUseCases = validationUseCase;
    }

    @Override
    public List<UserResponse> getAll(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty())
            throw new UserNotFoundException("Nenhum usuário cadastrado");

        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(UserResponse.mapToUserResponse(user));
        }

        return userResponses;
    }

    @Override
    public UserResponse getById(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado para o ID: " + id));

        return new UserResponse(user);
    }

    @Override
    public UserResponse getById(String id, String errorMessage) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(errorMessage));

        return new UserResponse(user);
    }

    @Override
    public UserResponse registerUser(User user) {
        //User validations
        validationUseCases.forEach(useCase -> useCase.validate(user));

        AddressExternalResponse addressExternalResponse = addressClient.getAddressByCep(user.getAddress().getCep());
        Address address = AddressExternalResponse.mapToAddress(addressExternalResponse);

        user.setAddress(address);

        userRepository.save(user);

        return new UserResponse(user);
    }

    @Override
    public UserResponse updateUser(String id, UserUpdateDto userUpdateDto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado para o ID: " + id));

        if(!userUpdateDto.getName().isEmpty()) user.setName(userUpdateDto.getName());

        if(!userUpdateDto.getCep().isEmpty()){
            AddressExternalResponse addressExternalResponse = addressClient.getAddressByCep(userUpdateDto.getCep());
            Address address = AddressExternalResponse.mapToAddress(addressExternalResponse);
            user.setAddress(address);
        }

        userRepository.save(user);

        return new UserResponse(user);
    }

    @Override
    public void deleteUser(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado para o ID: " + id));

        userRepository.delete(user);
    }
}

