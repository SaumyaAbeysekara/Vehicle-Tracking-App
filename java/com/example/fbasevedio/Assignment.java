package com.example.fbasevedio;

public class Assignment {
    private String vehicleAssign;
    private String driverAssign;
    private String driverAssignNO;

    public Assignment() {
    }

    public String getDriverAssignNO() {
        return driverAssignNO;
    }

    public void setDriverAssignNO(String driverAssignNO) {
        this.driverAssignNO = driverAssignNO;
    }

    public Assignment(String vehicleAssign, String driverAssign) {
        this.vehicleAssign = vehicleAssign;
        this.driverAssign = driverAssign;
    }

    public Assignment(String vehicleAssign, String driverAssign, String driverAssignNO) {
        if (vehicleAssign == null || driverAssign == null || driverAssignNO == null ||
                vehicleAssign.isEmpty() || driverAssign.isEmpty() || driverAssignNO.isEmpty()) {
            throw new IllegalArgumentException("All fields in Assignment must be non-null and non-empty");
        }
        this.vehicleAssign = vehicleAssign;
        this.driverAssign = driverAssign;
        this.driverAssignNO = driverAssignNO;
    }

    public String getVehicleAssign() {
        return vehicleAssign;
    }

    public void setVehicleAssign(String vehicleAssign) {
        this.vehicleAssign = vehicleAssign;
    }

    public String getDriverAssign() {
        return driverAssign;
    }

    public void setDriverAssign(String driverAssign) {
        this.driverAssign = driverAssign;
    }
}
