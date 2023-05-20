package com.engsoft.coursesapi.application.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engsoft.coursesapi.domain.models.User;
import com.engsoft.coursesapi.infraestructure.controllers.dtos.ListUsersReadDto;
import com.engsoft.coursesapi.infraestructure.repositories.UserRepository;

@Service
public class ListUsersUseCase {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> execute(ListUsersReadDto params) {
        String nameFilter = params.getName();
        String emailFilter = params.getEmail();

        return userRepository.findByFilter(nameFilter, emailFilter);
    }

}
