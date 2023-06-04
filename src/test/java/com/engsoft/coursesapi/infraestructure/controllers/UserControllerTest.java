package com.engsoft.coursesapi.infraestructure.controllers;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.engsoft.coursesapi.application.usecases.AssignPlanToUserUseCase;
import com.engsoft.coursesapi.application.usecases.ListUsersUseCase;
import com.engsoft.coursesapi.domain.models.User;
import com.engsoft.coursesapi.domain.models.valueobjects.Email;
import com.engsoft.coursesapi.domain.models.valueobjects.Password;
import com.engsoft.coursesapi.domain.models.valueobjects.TaxId;
import com.engsoft.coursesapi.infraestructure.controllers.dtos.ListUsersReadDto;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListUsersUseCase listUsersUseCase;

    @MockBean
    private AssignPlanToUserUseCase assignPlanToUserUseCase;

    @Test
    public void testGetUsers() throws Exception {
        String taxId = "123.456.789-00";
        String password = "sTrongPwd123@";

        // Defina o comportamento esperado do mock
        List<User> mockUsers = Arrays.asList(
            new User(1L, "John", Email.fromString("john@example.com"), TaxId.fromString(taxId), Password.fromString(password)),
            new User(2L, "Jane", Email.fromString("jane@example.com"), TaxId.fromString(taxId), Password.fromString(password))
        );
        
        when(listUsersUseCase.execute(any(ListUsersReadDto.class))).thenReturn(mockUsers);

        // Execute a solicitação GET /users
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[0].email").value("john@example.com"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane"))
                .andExpect(jsonPath("$[1].email").value("jane@example.com"));

        // Verifique se o caso de uso foi chamado com os parâmetros corretos
        ArgumentCaptor<ListUsersReadDto> captor = ArgumentCaptor.forClass(ListUsersReadDto.class);
        verify(listUsersUseCase).execute(captor.capture());
        ListUsersReadDto capturedParams = captor.getValue();
        assertNull(capturedParams.getName());
        assertNull(capturedParams.getEmail());
    }

    @Test
    public void testAssignPlanToUser() throws Exception {
        // Defina o comportamento esperado do mock
        doNothing().when(assignPlanToUserUseCase).execute(1L, 1L);

        // Execute a solicitação POST /users/{userId}/plan/{planId}
        mockMvc.perform(post("/users/1/plan/1"))
                .andExpect(status().isOk());

        // Verifique se o caso de uso foi chamado com os parâmetros corretos
        verify(assignPlanToUserUseCase).execute(1L, 1L);
    }
}

