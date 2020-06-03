package com.example.rxandroidlearning;

public class Task {


    private String description;
    private boolean isComplected;

    private int priority;

    public Task(String description, boolean isComplected, int priority) {
        this.description = description;
        this.isComplected = isComplected;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplected() {
        return isComplected;
    }

    public void setComplected(boolean complected) {
        isComplected = complected;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
