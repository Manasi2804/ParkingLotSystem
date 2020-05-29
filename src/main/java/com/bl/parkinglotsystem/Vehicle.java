package com.bl.parkinglotsystem;

public class Vehicle {
    DriverType driverType;
    private String vehicleId;
    VehicleSize vehicleSize;
    VehicleColour vehicleColour;

    public Vehicle(String vehicleId, DriverType driverType, VehicleSize vehicleSize, VehicleColour vehicleColour) {
        this.vehicleId = vehicleId;
        this.driverType = driverType;
        this.vehicleSize = vehicleSize;
        this.vehicleColour =  vehicleColour;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public enum DriverType {HANDICAP, NORMAL}
    public enum VehicleSize {LARGE, SMALL}
    public enum VehicleColour {
        WHITE
    }
}
