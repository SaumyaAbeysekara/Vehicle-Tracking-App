package com.example.fbasevedio;

public class MainModel {
    String vehicleNumber, ownerName,
            engineNumber,chassisNumber,ownerAddress,ownerNIC,ownerTP;



    MainModel(){

    }

    public MainModel(String vehicleNumber, String ownerName, String engineNumber, String chassisNumber, String ownerAddress, String ownerNIC, String ownerTP) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
        this.engineNumber = engineNumber;
        this.chassisNumber = chassisNumber;
        this.ownerAddress = ownerAddress;
        this.ownerNIC = ownerNIC;
        this.ownerTP = ownerTP;
    }


    public MainModel(String vehicleNumber, String ownerName) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getOwnerNIC() {
        return ownerNIC;
    }

    public void setOwnerNIC(String ownerNIC) {
        this.ownerNIC = ownerNIC;
    }

    public String getOwnerTP() {
        return ownerTP;
    }

    public void setOwnerTP(String ownerTP) {
        this.ownerTP = ownerTP;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
