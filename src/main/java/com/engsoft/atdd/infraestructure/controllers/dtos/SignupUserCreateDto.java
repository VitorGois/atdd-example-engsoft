package com.engsoft.atdd.infraestructure.controllers.dtos;

public class SignupUserCreateDto {
	
	private String name;
	private String email;
	private String password;

	public SignupUserCreateDto(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}	
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
}
