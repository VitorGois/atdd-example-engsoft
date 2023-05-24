package com.engsoft.coursesapi.application.validators;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import com.engsoft.coursesapi.infraestructure.exceptions.BadRequestException;

public class SignupUserCreateDtoValidatorTest {

    @Test
    public void testValidateEmail_ValidEmail_DoesNotThrowException() {
        // Arrange
        String email = "test@example.com";

        // Act and Assert
        assertDoesNotThrow(() -> SignupUserCreateDtoValidator.validateEmail(email));
    }

    @Test
    public void testValidateEmail_InvalidEmail_ThrowsBadRequestException() {
        // Arrange
        String email = "invalid-email";

        // Act and Assert
        assertThrows(BadRequestException.class, () -> SignupUserCreateDtoValidator.validateEmail(email));
    }

    @Test
    public void testValidatePassword_ValidPassword_DoesNotThrowException() {
        // Arrange
        String password = "Abc@1234";

        // Act and Assert
        assertDoesNotThrow(() -> SignupUserCreateDtoValidator.validatePassword(password));
    }

    @Test
    public void testValidatePassword_InvalidPassword_ThrowsBadRequestException() {
        // Arrange
        String password = "weakpassword";

        // Act and Assert
        assertThrows(BadRequestException.class, () -> SignupUserCreateDtoValidator.validatePassword(password));
    }
}
