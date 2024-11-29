package com.example.FitTrack;

public class Plan {
    private String planName;
    private String description;
    private String assignments;

    public Plan(String planName, String description, String assignments) {
        this.planName = planName;
        this.description = description;
        this.assignments = assignments;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignments() {
        return assignments;
    }

    public void setAssignments(String assignments) {
        this.assignments = assignments;
    }
}



