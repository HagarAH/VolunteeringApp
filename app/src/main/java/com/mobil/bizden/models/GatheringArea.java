package com.mobil.bizden.models;

import com.mobil.bizden.models.TimeRequirement;

import java.util.List;

public class GatheringArea {
    private String aid;
    private String name;
    private int capacity;
    private String province;
    private String district;
    private String status;
    private List<TimeRequirement> timeRequirements;

    public GatheringArea() {
        // Default constructor required for Firebase
    }

    public GatheringArea(String aid, String name, int capacity, String province, String district, String status, List<TimeRequirement> timeRequirements) {
        this.aid = aid;
        this.name = name;
        this.capacity = capacity;
        this.province = province;
        this.district = district;
        this.status = status;
        this.timeRequirements = timeRequirements;
    }

    // Getters and setters

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TimeRequirement> getTimeRequirements() {
        return timeRequirements;
    }

    public void setTimeRequirements(List<TimeRequirement> timeRequirements) {
        this.timeRequirements = timeRequirements;
    }
}
