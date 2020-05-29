package com.bl.parkinglotsystem;

public class Vehicle {
    DriverType driverType;
    private String vehicleId;

    public Vehicle(String vehicleId, DriverType driverType) {
        this.vehicleId = vehicleId;
        this.driverType = driverType;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public enum DriverType {HANDICAP, NORMAL}
}
