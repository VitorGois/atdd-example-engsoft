package com.engsoft.coursesapi.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engsoft.coursesapi.application.validators.SignupUserCreateDtoValidator;
import com.engsoft.coursesapi.domain.builders.UserFactory;
import com.engsoft.coursesapi.domain.models.User;
import com.engsoft.coursesapi.infraestructure.controllers.dtos.SignupUserCreateDto;
import com.engsoft.coursesapi.infraestructure.exceptions.BadRequestException;
import com.engsoft.coursesapi.infraestructure.exceptions.UserAlreadyExistsException;
import com.engsoft.coursesapi.infraestructure.repositories.UserRepository;

@Service
public class SignupUserUseCase {

	@Autowired
	private UserRepository userRepository;

    public User execute(SignupUserCreateDto params) throws UserAlreadyExistsException, BadRequestException {
        String email = params.getEmail();
        String password = params.getPassword();

        SignupUserCreateDtoValidator.validateEmail(email);
        SignupUserCreateDtoValidator.validatePassword(password);
        
        try {
        	User alreadyUser = userRepository.findByEmail(email);

            if (alreadyUser != null) {
                throw new UserAlreadyExistsException("user with email/taxId already exists");
            }

            User user = new UserFactory()
                .withSignupParams(params)
                .build();
    
            return userRepository.save(user);
        } catch (Error e) {
        	throw e;
        }
    }

}
