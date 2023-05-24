package com.engsoft.coursesapi.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.engsoft.coursesapi.domain.models.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
