package com.mobil.bizden.models;

public class Request {
    private String aid;
    private String uid;
    private String creationDate;
    private boolean acceptance;
    private String volunteerStartTime;
    private String volunteerEndTime;

    public Request() {
        // Default constructor required for Firestore
    }

    public Request(String aid, String uid, String creationDate, boolean acceptance, String volunteerStartTime, String volunteerEndTime) {
        this.aid = aid;
        this.uid = uid;
        this.creationDate = creationDate;
        this.acceptance = acceptance;
        this.volunteerStartTime = volunteerStartTime;
        this.volunteerEndTime = volunteerEndTime;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public String getVolunteerStartTime() {
        return volunteerStartTime;
    }

    public void setVolunteerStartTime(String volunteerStartTime) {
        this.volunteerStartTime = volunteerStartTime;
    }

    public String getVolunteerEndTime() {
        return volunteerEndTime;
    }

    public void setVolunteerEndTime(String volunteerEndTime) {
        this.volunteerEndTime = volunteerEndTime;
    }
}
