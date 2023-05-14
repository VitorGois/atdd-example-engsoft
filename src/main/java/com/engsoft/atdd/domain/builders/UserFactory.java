package com.engsoft.atdd.domain.builders;

import com.engsoft.atdd.domain.models.User;
import com.engsoft.atdd.domain.models.valueobjects.Email;
import com.engsoft.atdd.infraestructure.controllers.dtos.SignupUserCreateDto;

public class UserFactory {

	private User user;
	
    public UserFactory signupParams(SignupUserCreateDto params) {
        Email emailObj = Email.fromString(params.getEmail());
        this.user = new User(params.getName(), emailObj, params.getPassword());        
        return this;
    }
    
    public User create() {
    	return this.user;
    }
	
}
