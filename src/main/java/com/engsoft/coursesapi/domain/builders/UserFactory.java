package com.engsoft.coursesapi.domain.builders;

import com.engsoft.coursesapi.domain.models.User;
import com.engsoft.coursesapi.domain.models.valueobjects.Email;
import com.engsoft.coursesapi.domain.models.valueobjects.Password;
import com.engsoft.coursesapi.domain.models.valueobjects.TaxId;
import com.engsoft.coursesapi.infraestructure.controllers.dtos.SignupUserCreateDto;

public class UserFactory {

	private User user;
	
    public UserFactory withSignupParams(SignupUserCreateDto params) {
        Email emailObj = Email.fromString(params.getEmail());
        TaxId taxIdObj = TaxId.fromString(params.getTaxId());
        Password passwordObj = Password.fromString(params.getPassword());

        this.user = new User(params.getName(), emailObj, taxIdObj, passwordObj);    
        return this;
    }
    
    public User build() {
    	return this.user;
    }
	
}
