package com.engsoft.coursesapi.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.engsoft.coursesapi.domain.models.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
