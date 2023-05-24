package com.engsoft.coursesapi.application.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {

    @Test
    public void testValidate_ValidPassword_ReturnsTrue() {
        // Arrange
        String password = "Abc@1234";

        // Act
        boolean isValid = PasswordValidator.validate(password);

        // Assert
        assertTrue(isValid);
    }

    @Test
    public void testValidate_InvalidPassword_ReturnsFalse() {
        // Arrange
        String password = "weakpassword";

        // Act
        boolean isValid = PasswordValidator.validate(password);

        // Assert
        assertFalse(isValid);
    }
    
}
