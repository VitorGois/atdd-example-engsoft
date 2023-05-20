package com.engsoft.coursesapi.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engsoft.coursesapi.domain.builders.PlanFactory;
import com.engsoft.coursesapi.domain.models.Plan;
import com.engsoft.coursesapi.infraestructure.repositories.PlanRepository;

@Service
public class CreatePlanUseCase {

    @Autowired
    private PlanRepository planRepository;

    public Plan execute(String name, String description) {
        Plan plan = new PlanFactory()
            .withName(name)
            .withDescription(description)
            .build();
        
        return planRepository.save(plan);
    }
}
