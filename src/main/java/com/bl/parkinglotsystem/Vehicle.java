package com.bl.parkinglotsystem;

public class Vehicle {
    String attendantName;
    VehicleModel vehicleModel;
    VehicleColour vehicleColour;
    VehicleSize vehicleSize;
    DriverType driverType;
    private String vehicleId;

    public Vehicle(String vehicleId, DriverType driverType, VehicleSize vehicleSize,
                VehicleColour vehicleColour, VehicleModel vehicleModel, String attendantName) {

            this.vehicleId = vehicleId;
            this.driverType = driverType;
            this.vehicleSize = vehicleSize;
            this.vehicleColour =  vehicleColour;
            this.vehicleModel = vehicleModel;
            this.attendantName = attendantName;
        }
        public String getVehicleId() {
            return vehicleId;
        }
        public enum DriverType {
            HANDICAP, NORMAL
        }
        public enum VehicleSize {
            LARGE, SMALL
        }
        public enum VehicleColour {
            WHITE,BLUE
        }
        public enum VehicleModel {
         TOYOTA
        }
    }
