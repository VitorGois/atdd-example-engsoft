package com.engsoft.coursesapi.infraestructure.controllers.dtos;

import com.engsoft.coursesapi.domain.models.Plan;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Plan plan;

    public UserDto(Long id, String name, String email, Plan plan) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.plan = plan;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Plan getPlan() {
        return plan;
    }
    
}
