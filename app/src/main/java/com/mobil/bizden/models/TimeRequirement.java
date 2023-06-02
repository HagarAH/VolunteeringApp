package com.mobil.bizden.models;

public class TimeRequirement {
    private String start;
    private String end;
    private int requiredVolunteers;

    public TimeRequirement() {
        // Default constructor required for Firebase
    }

    public TimeRequirement(String start, String end, int requiredVolunteers) {
        this.start = start;
        this.end = end;
        this.requiredVolunteers = requiredVolunteers;
    }

    // Getters and setters

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getRequiredVolunteers() {
        return requiredVolunteers;
    }

    public void setRequiredVolunteers(int requiredVolunteers) {
        this.requiredVolunteers = requiredVolunteers;
    }
}
