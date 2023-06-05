package com.mobil.bizden.models;

public class Request {
    private String aid;
    private String uid;
    private String creationDate;
    private boolean acceptance;
    private boolean rejection;
    private String volunteerStartTime;
    private String volunteerEndTime;
    private String did;

    public Request() {
        // Default constructor required for Firestore
    }

    public Request(String aid, String did,String uid, String creationDate, boolean acceptance,boolean rejection, String volunteerStartTime, String volunteerEndTime) {
        this.did= did;
        this.aid = aid;
        this.uid = uid;
        this.creationDate = creationDate;
        this.rejection = rejection;
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
    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
   public boolean isRejection() {
        return rejection;
    }

    public void setRejection(boolean rejection) {
        this.rejection = rejection;
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
