package com.mobil.bizden.models;
public class EntryCode {
    private String id;
    private String aid;

    private String did;
    private String uid;
    private String creationDate;
    private String validUntil;
    private String code;

    private boolean status;

    public EntryCode() {
        // Default constructor required for Firestore
    }

    public EntryCode(String id, String aid, String uid, String did,String creationDate, String validUntil, String code, boolean status) {
        this.id = id;
        this.aid = aid;
        this.uid = uid;
        this.did=did;
        this.creationDate = creationDate;
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
        return creationDate;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public String getCode() {
        return code;
    }
}
