package com.engsoft.atdd.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engsoft.atdd.application.validators.SignupUserCreateDtoValidator;
import com.engsoft.atdd.domain.builders.UserFactory;
import com.engsoft.atdd.domain.models.User;
import com.engsoft.atdd.infraestructure.controllers.dtos.SignupUserCreateDto;
import com.engsoft.atdd.infraestructure.exceptions.BadRequestException;
import com.engsoft.atdd.infraestructure.exceptions.EmailAlreadyExistsException;
import com.engsoft.atdd.infraestructure.repositories.UserRepository;

@Service
public class SignupUserUseCase {

	@Autowired
	private UserRepository userRepository;

    public User execute(SignupUserCreateDto params) throws EmailAlreadyExistsException, BadRequestException {
        String email = params.getEmail();
        String password = params.getPassword();
    	
        SignupUserCreateDtoValidator.validateEmail(email);
        SignupUserCreateDtoValidator.validatePassword(password);
        
        try {
        	User alreadyUser = userRepository.findByEmail(email);

            if (alreadyUser != null) {
                throw new EmailAlreadyExistsException("email already exists");
            }

            User user = new UserFactory()
                .signupParams(params)
                .create();
    
            return userRepository.save(user);
        } catch (Error e) {
        	throw e;
        }
    }

}
