package com.engsoft.atdd.domain.builders;

import com.engsoft.atdd.domain.models.Plan;

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
