package com.engsoft.atdd.application.usecases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.engsoft.atdd.domain.builders.PlanFactory;
import com.engsoft.atdd.domain.models.Plan;
import com.engsoft.atdd.infraestructure.repositories.PlanRepository;

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
