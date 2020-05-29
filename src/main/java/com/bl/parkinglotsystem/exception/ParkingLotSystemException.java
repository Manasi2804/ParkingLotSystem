package com.bl.parkinglotsystem.exception;

public class ParkingLotSystemException  extends RuntimeException {
    public enum ExceptionType {
            NOT_PARKED_HERE, VEHICLE_ALREADY_PARKED, PARKING_LOT_IS_FULL;
    }
    public ExceptionType type;
    public ParkingLotSystemException( ExceptionType type,String message) {
        super(message);
        this.type = type;
    }
}