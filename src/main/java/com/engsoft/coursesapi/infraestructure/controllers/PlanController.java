package com.engsoft.coursesapi.infraestructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engsoft.coursesapi.application.usecases.CreatePlanUseCase;
import com.engsoft.coursesapi.domain.models.Plan;
import com.engsoft.coursesapi.infraestructure.controllers.dtos.PlanCreateDto;

@RestController
@CrossOrigin
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private CreatePlanUseCase createPlanUseCase;

    @PostMapping
    public ResponseEntity<Plan> createPlan(@RequestBody PlanCreateDto request) {
        try {
            Plan plan = createPlanUseCase.execute(request.getName(), request.getDescription());
            return ResponseEntity.status(HttpStatus.CREATED).body(plan);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
