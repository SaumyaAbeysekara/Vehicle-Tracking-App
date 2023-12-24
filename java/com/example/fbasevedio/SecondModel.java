package com.example.fbasevedio;

public class SecondModel {
    String TP,driverCode,driverName,licenceEX,licenceNumber,url;

    public SecondModel(String TP, String driverCode, String driverName, String licenceEX, String licenceNumber , String url) {
        this.TP = TP;
        this.driverCode = driverCode;
        this.driverName = driverName;
        this.licenceEX = licenceEX;
        this.licenceNumber = licenceNumber;
        this.url = url;
    }

    public SecondModel() {

    }

    public String getTP() {
        return TP;
    }

    public void setTP(String TP) {
        this.TP = TP;
    }

    public String getDriverCode() {
        return driverCode;
    }

    public void setDriverCode(String driverCode) {
        this.driverCode = driverCode;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLicenceEX() {
        return licenceEX;
    }

    public void setLicenceEX(String licenceEX) {
        this.licenceEX = licenceEX;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
