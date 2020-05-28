package com.bl.parkinglotsystem.exception;

public class ParkingLotSystemException  extends RuntimeException {
    public enum ExceptionType {
        PARKING_FULL, NO_VEHICLE, CAR_ALREADY_PARKED;
    }
    public ExceptionType type;
    public ParkingLotSystemException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}