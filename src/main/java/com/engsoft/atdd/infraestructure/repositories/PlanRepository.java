package com.engsoft.atdd.infraestructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.engsoft.atdd.domain.models.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
