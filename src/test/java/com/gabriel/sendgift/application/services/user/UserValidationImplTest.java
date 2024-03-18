package com.gabriel.sendgift.application.services.user;

import com.gabriel.sendgift.application.exceptions.EmailAlreadyExistsException;
import com.gabriel.sendgift.application.exceptions.InvalidPasswordException;
import com.gabriel.sendgift.core.domain.user.User;
import com.gabriel.sendgift.core.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserValidationImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserEmailValidationImpl userEmailValidation;

    @InjectMocks
    private UserPasswordValidationImpl userPasswordValidation;

    @Test
    public void testUserEmailValidation_EmailExists() {
        User user = new User();
        user.setEmail("existing@example.com");
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
        assertThrows(EmailAlreadyExistsException.class, () -> userEmailValidation.validate(user));
    }

    @Test
    public void testUserEmailValidation_EmailDoesNotExist() {
        User user = new User();
        user.setEmail("new@example.com");
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        userEmailValidation.validate(user);
    }

    /* Password Test's */

    @Test
    public void testUserPasswordValidation_ValidPassword() {
        User user = new User();
        user.setPassword("Abcdefg1@");
        userPasswordValidation.validate(user);
    }

    @Test
    public void testUserPasswordValidation_ShortPassword() {
        User user = new User();
        user.setPassword("short");
        assertThrows(InvalidPasswordException.class, () -> userPasswordValidation.validate(user));
    }

    @Test
    public void testUserPasswordValidation_PasswordWithoutUpperCase() {
        User user = new User();
        user.setPassword("abcdefg1@");
        assertThrows(InvalidPasswordException.class, () -> userPasswordValidation.validate(user));
    }

    @Test
    public void testUserPasswordValidation_PasswordWithoutLowerCase() {
        User user = new User();
        user.setPassword("ABCDEFG1@");
        assertThrows(InvalidPasswordException.class, () -> userPasswordValidation.validate(user));
    }

    @Test
    public void testUserPasswordValidation_PasswordWithoutNumber() {
        User user = new User();
        user.setPassword("Abcdefg@");
        assertThrows(InvalidPasswordException.class, () -> userPasswordValidation.validate(user));
    }

    @Test
    public void testUserPasswordValidation_PasswordWithoutSpecialCharacter() {
        User user = new User();
        user.setPassword("Abcdefg1");
        assertThrows(InvalidPasswordException.class, () -> userPasswordValidation.validate(user));
    }
}
