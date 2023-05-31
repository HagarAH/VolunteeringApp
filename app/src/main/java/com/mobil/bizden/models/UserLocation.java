package com.mobil.bizden.models;

public class UserLocation {
    private String uid;
    private String province;
    private String district;
    private String address;

    public UserLocation() {
        // Default constructor required for Firestore
    }

    public UserLocation(String uid, String province, String district, String address) {
        this.uid = uid;
        this.province = province;
        this.district = district;
        this.address = address;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
