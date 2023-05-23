package com.mobil.bizden.models;
import java.util.Date;

public class Profile {
    private String uid;
    private String firstName;
    private String lastName;
    private String telephone;
    private String tcId;
    private Date birthDate;

    public Profile() {
        // Default constructor required for Firestore
    }

    public Profile(String uid, String firstName, String lastName, String telephone, String tcId, Date birthDate) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.tcId = tcId;
        this.birthDate = birthDate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTcId() {
        return tcId;
    }

    public void setTcId(String tcId) {
        this.tcId = tcId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
