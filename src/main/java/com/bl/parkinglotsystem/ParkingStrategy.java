package com.bl.parkinglotsystem;

import com.bl.parkinglotsystem.exception.ParkingLotSystemException;

import java.util.List;

public interface ParkingStrategy {
    public ParkingLotSystem getParkingLot(List<ParkingLotSystem> parkingLotList) throws ParkingLotSystemException;
}
