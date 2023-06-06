package com.mobil.bizden.models;

import com.google.firebase.Timestamp;

public class EntryCode {
    private String id;
    private String aid;

    private String did;
    private String uid;
    private String createdDate;
    private Timestamp validUntil;
    private String code;

    private boolean status;

    public EntryCode() {
        // Default constructor required for Firestore
    }

    public EntryCode( String aid, String uid, String did,String createdDate, Timestamp validUntil, String code, boolean status) {
        this.aid = aid;
        this.uid = uid;
        this.did=did;
        this.createdDate = createdDate;
        this.validUntil = validUntil;
        this.code = code;
        this.status= status;
    }

    public String getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public String getAid() {
        return aid;
    }
    public String getDid(){
        return did;
    }

    public String getUid() {
        return uid;
    }

    public String getCreationDate() {
        return createdDate;
    }

    public Timestamp getValidUntil() {
        return validUntil;
    }

    public String getCode() {
        return code;
    }
}
