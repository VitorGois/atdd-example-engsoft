package com.engsoft.coursesapi.application.validators;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;


public class EmailValidatorTest {

    @Test
    public void testValidate_ValidEmail_ReturnsTrue() {
        // Arrange
        String email = "example@example.com";

        // Act
        boolean isValid = EmailValidator.validate(email);

        // Assert
        assertTrue(isValid);
    }

    @Test
    public void testValidate_InvalidEmail_ReturnsFalse() {
        // Arrange
        String email = "example";

        // Act
        boolean isValid = EmailValidator.validate(email);

        // Assert
        assertFalse(isValid);
    }
    
}
