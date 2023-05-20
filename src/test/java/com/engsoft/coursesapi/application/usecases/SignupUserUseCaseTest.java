package com.engsoft.coursesapi.application.usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.engsoft.coursesapi.domain.models.User;
import com.engsoft.coursesapi.domain.models.valueobjects.Email;
import com.engsoft.coursesapi.domain.models.valueobjects.Password;
import com.engsoft.coursesapi.domain.models.valueobjects.TaxId;
import com.engsoft.coursesapi.infraestructure.controllers.dtos.SignupUserCreateDto;
import com.engsoft.coursesapi.infraestructure.exceptions.BadRequestException;
import com.engsoft.coursesapi.infraestructure.exceptions.UserAlreadyExistsException;
import com.engsoft.coursesapi.infraestructure.repositories.UserRepository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userRepository.save(any(User.class)))
            .thenReturn(new User(1L, name, Email.fromString(email), TaxId.fromString(taxId), Password.fromString(password)));

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
        when(userRepository.findByEmail(anyString()))
            .thenReturn(new User(1L, name, Email.fromString(email), TaxId.fromString(taxId), Password.fromString(password)));

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
