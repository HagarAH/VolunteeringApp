package com.mobil.bizden.models;

public class GatheringAreaInfo {
    private String aid;
    private String address;
    private double occupancyRate;
    private String information;
    private String organization;

    public GatheringAreaInfo(String aid, String address, double occupancyRate, String information, String organization) {
        this.aid = aid;
        this.address = address;
        this.occupancyRate = occupancyRate;
        this.information = information;
        this.organization = organization;
    }

    public String getAid() {
        return aid;
    }

    public String getAddress() {
        return address;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }

    public String getInformation() {
        return information;
    }

    public String getOrganization() {
        return organization;
    }
}
