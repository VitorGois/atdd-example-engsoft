package com.engsoft.atdd.infraestructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engsoft.atdd.application.usecases.SignupUserUseCase;
import com.engsoft.atdd.infraestructure.controllers.dtos.SignupUserCreateDto;
import com.engsoft.atdd.infraestructure.exceptions.BadRequestException;
import com.engsoft.atdd.infraestructure.exceptions.UserAlreadyExistsException;


@RestController
@CrossOrigin
@RequestMapping("/signup")
public class SignupController {
	
	@Autowired
	private SignupUserUseCase signupUserUseCase;
	
	@PostMapping
	public ResponseEntity<String> signup(@RequestBody SignupUserCreateDto body) {
		try {
			signupUserUseCase.execute(body);
			return ResponseEntity.status(HttpStatus.CREATED).body(null);
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
		} catch (BadRequestException e) {
			return ResponseEntity.badRequest().body("Bad request: " + e.getMessage());
		} catch (Error e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
		}
	}

}
