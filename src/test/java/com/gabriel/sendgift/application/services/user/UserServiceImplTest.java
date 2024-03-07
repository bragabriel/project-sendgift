package com.gabriel.sendgift.application.services.user;

import com.gabriel.sendgift.application.exceptions.UserNotFoundException;
import com.gabriel.sendgift.core.adapter.AddressClient;
import com.gabriel.sendgift.core.domain.address.dto.AddressExternalResponse;
import com.gabriel.sendgift.core.domain.user.User;
import com.gabriel.sendgift.core.domain.user.dto.UserResponse;
import com.gabriel.sendgift.core.domain.user.dto.UserUpdateDto;
import com.gabriel.sendgift.core.repositories.UserRepository;
import com.gabriel.sendgift.core.usecases.user.UserValidationUseCase;
import com.gabriel.sendgift.fixture.user.UserFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AddressClient addressService;

    @Mock
    private List<UserValidationUseCase> validationUseCases;

    @Test
    void getAllUsers_shouldReturnAllUsers() {
        // Arrange
        List<User> users = new ArrayList<>();
        users.add(UserFixture.createUser());
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserResponse> userResponses = userService.getAll();

        // Assert
        assertEquals(1, userResponses.size());
        assertEquals("Gabriel", userResponses.get(0).getName());
    }

    @Test
    void getById_shouldReturnUserById() {
        // Arrange
        User user = UserFixture.createUser();
        when(userRepository.findById("123xd321aA")).thenReturn(Optional.of(user));

        // Act
        UserResponse userResponse = userService.getById("123xd321aA");

        // Assert
        assertEquals("Gabriel", userResponse.getName());
    }

    @Test
    void getUserById_whenIdNotExist_shouldReturnUserNotFoundException() {
        // Arrange
        String userId = "";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(
                UserNotFoundException.class, () -> userService.getById(userId)
        );
    }

    @Test
    void registerUser_shouldCreateThanReturnUserCreated() {
        // Arrange
        User user = UserFixture.createUser();
        when(addressService.getAddressByCep(anyString())).thenReturn(new AddressExternalResponse());

        // Act
        UserResponse userResponse = userService.registerUser(user);

        // Assert
        assertNotNull(userResponse);
        assertEquals("123xd321aA", userResponse.getId());
        assertEquals("Gabriel", userResponse.getName());
    }

    @Test
    void updateUser_shouldUpdateThanReturnUserUpdated() {
        // Arrange
        User user = UserFixture.createUser();
        UserUpdateDto userUpdateDto = new UserUpdateDto("Jane", "12345555");
        when(userRepository.findById("123xd321aA")).thenReturn(Optional.of(user));
        when(addressService.getAddressByCep(anyString())).thenReturn(new AddressExternalResponse());

        // Act
        UserResponse updatedUserResponse = userService.updateUser("123xd321aA", userUpdateDto);

        // Assert
        assertEquals("123xd321aA", updatedUserResponse.getId());
        assertEquals("Jane", updatedUserResponse.getName());
    }

    @Test
    void deleteUser_shouldDeleteUserById() {
        // Arrange
        User user = UserFixture.createUser();;
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser("1");

        // Assert
        Mockito.verify(userRepository, times(1)).delete(user);
    }
}
