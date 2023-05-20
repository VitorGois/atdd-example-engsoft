package com.engsoft.atdd.application.usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.engsoft.atdd.domain.models.User;
import com.engsoft.atdd.domain.models.valueobjects.Email;
import com.engsoft.atdd.domain.models.valueobjects.TaxId;
import com.engsoft.atdd.infraestructure.controllers.dtos.SignupUserCreateDto;
import com.engsoft.atdd.infraestructure.exceptions.BadRequestException;
import com.engsoft.atdd.infraestructure.exceptions.UserAlreadyExistsException;
import com.engsoft.atdd.infraestructure.repositories.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class SignupUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SignupUserUseCase signupUserUseCase;

    @Test
    public void testExecute_SuccessfulSignup() throws Exception {
        // Arrange
        String name = "name";
        String email = "test@example.com";
        String taxId = "123.456.789-00";
        String password = "sTrongPwd123@";
        SignupUserCreateDto params = new SignupUserCreateDto(name, email, taxId, password);

        // Mock the behavior of UserRepository
        when(userRepository.findByEmailOrTaxId(anyString(), anyString())).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(new User(1L, name, Email.fromString(email), TaxId.fromString(taxId), password));

        // Act
        User result = signupUserUseCase.execute(params);

        // Assert
        assertNotNull(result);
    }

    @Test(expected = UserAlreadyExistsException.class)
    public void testExecute_EmailAlreadyExists() throws Exception {
        // Arrange
        String name = "name";
        String email = "test@example.com";
        String taxId = "123.456.789-00";
        String password = "sTrongPwd123@";
        SignupUserCreateDto params = new SignupUserCreateDto(name, email, taxId, password);

        // Mock the behavior of UserRepository
        when(userRepository.findByEmailOrTaxId(anyString(), anyString())).thenReturn(new User(1L, name, Email.fromString(email), TaxId.fromString(taxId), password));

        // Act
        signupUserUseCase.execute(params);

        // Expect EmailAlreadyExistsException to be thrown
    }

    @Test(expected = BadRequestException.class)
    public void testExecute_InvalidPassword() throws Exception {
        // Arrange
        String name = "name";
        String email = "test@example.com";
        String taxId = "123.456.789-00";
        String password = "password";
        SignupUserCreateDto params = new SignupUserCreateDto(name, email, taxId, password);

        // Act
        signupUserUseCase.execute(params);

        // Expect BadRequestException to be thrown
    }
}
