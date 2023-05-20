package com.engsoft.coursesapi.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engsoft.coursesapi.domain.models.Plan;
import com.engsoft.coursesapi.domain.models.User;
import com.engsoft.coursesapi.infraestructure.exceptions.NotFoundException;
import com.engsoft.coursesapi.infraestructure.repositories.PlanRepository;
import com.engsoft.coursesapi.infraestructure.repositories.UserRepository;

@Service
public class AssignPlanToUserUseCase {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PlanRepository planRepository;

    public void execute(Long userId, Long planId) throws NotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("user not found"));

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new NotFoundException("plan not found"));

        user.setPlan(plan);
        userRepository.save(user);
    }
}
