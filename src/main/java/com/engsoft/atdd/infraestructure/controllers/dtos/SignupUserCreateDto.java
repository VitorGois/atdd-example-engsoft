package com.engsoft.atdd.infraestructure.controllers.dtos;

public class SignupUserCreateDto {
	
	private String name;
	private String email;
	private String taxId;
	private String password;

	public SignupUserCreateDto(String name, String email, String taxId, String password) {
		this.name = name;
		this.email = email;
		this.taxId = taxId;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getTaxId() {
		return taxId;
	}

	public String getPassword() {
		return password;
	}
	
}
