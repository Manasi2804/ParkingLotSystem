package com.bl.parkinglotsystem;

public class Vehicle {
    String attendantName;
    VehicleModel vehicleModel;
    VehicleColour vehicleColour;
    VehicleSize vehicleSize;
    DriverType driverType;
    

    public Vehicle( DriverType driverType, VehicleSize vehicleSize,
                VehicleColour vehicleColour, VehicleModel vehicleModel, String attendantName) {
            this.driverType = driverType;
            this.vehicleSize = vehicleSize;
            this.vehicleColour =  vehicleColour;
            this.vehicleModel = vehicleModel;
            this.attendantName = attendantName;
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
         TOYOTA,BMW
        }
    }
