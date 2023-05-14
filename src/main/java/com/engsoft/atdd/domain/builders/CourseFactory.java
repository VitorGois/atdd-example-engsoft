package com.engsoft.atdd.domain.builders;

import com.engsoft.atdd.domain.models.Course;
import com.engsoft.atdd.domain.models.Plan;

public class CourseFactory {
    
    private String name;
    private String description;
    private Plan plan;

    public CourseFactory withName(String name) {
        this.name = name;
        return this;
    }

    public CourseFactory withDescription(String description) {
        this.description = description;
        return this;
    }

    public CourseFactory withPlan(Plan plan) {
        this.plan = plan;
        return this;
    }

    public Course build() {
        return new Course(this.name, this.description, this.plan);
    }

}
