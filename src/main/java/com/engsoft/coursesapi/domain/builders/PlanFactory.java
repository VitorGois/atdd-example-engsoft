package com.engsoft.coursesapi.domain.builders;

import com.engsoft.coursesapi.domain.models.Plan;

public class PlanFactory {

    private String name;
    private String description;

    public PlanFactory withName(String name) {
        this.name = name;
        return this;
    }

    public PlanFactory withDescription(String description) {
        this.description = description;
        return this;
    }

    public Plan build() {
        return new Plan(this.name, this.description);
    }
    
}
