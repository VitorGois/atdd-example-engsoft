package com.engsoft.coursesapi.application.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.engsoft.coursesapi.domain.models.Plan;
import com.engsoft.coursesapi.domain.models.User;
import com.engsoft.coursesapi.infraestructure.exceptions.NotFoundException;
import com.engsoft.coursesapi.infraestructure.repositories.PlanRepository;
import com.engsoft.coursesapi.infraestructure.repositories.UserRepository;


@ExtendWith(MockitoExtension.class)
public class AssignPlanToUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PlanRepository planRepository;

    @InjectMocks
    private AssignPlanToUserUseCase assignPlanToUserUseCase;

    @Test
    public void testExecute_WhenUserAndPlanExist_ShouldAssignPlanToUser() throws Exception {
        // Arrange
        Long userId = 1L;
        Long planId = 1L;
        User user = new User(userId, null, null, null, null);
        Plan plan = new Plan(planId, null, null);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(planRepository.findById(planId)).thenReturn(Optional.of(plan));
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // Act
        assignPlanToUserUseCase.execute(userId, planId);

        // Assert
        Mockito.verify(userRepository).findById(userId);
        Mockito.verify(planRepository).findById(planId);
        Mockito.verify(userRepository).save(user);
        assertEquals(plan, user.getPlan());
    }

    @Test
    public void testExecute_WhenUserNotFound_ShouldThrowNotFoundException() throws Exception {
        // Arrange
        Long userId = 1L;
        Long planId = 1L;

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            assignPlanToUserUseCase.execute(userId, planId);
        });
    }

    @Test
    public void testExecute_WhenPlanNotFound_ShouldThrowNotFoundException() throws Exception {
        // Arrange
        Long userId = 1L;
        Long planId = 1L;
        User user = new User(userId, null, null, null, null);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(planRepository.findById(planId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            assignPlanToUserUseCase.execute(userId, planId);
        });
    }
}
