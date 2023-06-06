package com.mobil.bizden.models;

import com.google.firebase.Timestamp;

import java.sql.Time;

public class EntryCode {
    private String id;
    private String aid;

    private String did;
    private String uid;
    private Timestamp createdDate;
    private Timestamp validUntil;
    private String code;

    private boolean status;

    public EntryCode() {
        // Default constructor required for Firestore
    }

    public EntryCode( String aid, String uid, String did,Timestamp createdDate, Timestamp validUntil, String code, boolean status) {
        this.aid = aid;
        this.uid = uid;
        this.did=did;
        this.createdDate = createdDate;
        this.validUntil = validUntil;
        this.code = code;
        this.status= status;
    }


    public boolean getStatus() {
        return status;
    }
    public void setStatus( boolean status) {
        this.status=status;
    }

    public String getAid() {
        return aid;
    }
    public void setAid(String aid) {
         this.aid= aid;
    }   public void setDid(String did) {
         this.did= did;
    }   public void setUid(String uid) {
         this.uid= uid;
    }
    public String getDid(){
        return did;
    }

    public String getUid() {
        return uid;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp date) {
         this.createdDate= date;
    }


    public Timestamp getValidUntil() {
        return validUntil;
    }

    public String getCode() {
        return code;
    }
}
