package com.engsoft.coursesapi.infraestructure.controllers.dtos;

public class ListUsersReadDto {

    private String name;
	private String email;

	public ListUsersReadDto(String name, String email) {
		this.name = name;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
    
}
