package com.bryle_sanico.casestudy;
public class UnitModel {
    private String unitID;
    private String locName;

    private String unitThumbnail;
    private String locAddress;
    private String paymentAmount;

    public UnitModel(String unitID, String unitThumbnail, String locName, String locAddress, String paymentAmount) {
        this.unitID = unitID;
        this.unitThumbnail = unitThumbnail;
        this.locName = locName;
        this.locAddress = locAddress;
        this.paymentAmount = paymentAmount;
    }

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getUnitThumbnail() {
        return unitThumbnail;
    }

    public void setUnitThumbnail(String unitThumbnail) {
        this.unitID = unitThumbnail;
    }
    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getLocAddress() {
        return locAddress;
    }

    public void setLocAddress(String locAddress) {
        this.locAddress = locAddress;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
