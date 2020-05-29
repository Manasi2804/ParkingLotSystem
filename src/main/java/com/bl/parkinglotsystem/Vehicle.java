package com.bl.parkinglotsystem;

public class Vehicle {
    DriverType driverType;
    private String vehicleId;
    VehicleSize vehicleSize;

    public Vehicle(String vehicleId, DriverType driverType, VehicleSize vehicleSize) {
        this.vehicleId = vehicleId;
        this.driverType = driverType;
        this.vehicleSize = vehicleSize;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public enum DriverType {HANDICAP, NORMAL}
    public enum VehicleSize {LARGE, SMALL}
}
