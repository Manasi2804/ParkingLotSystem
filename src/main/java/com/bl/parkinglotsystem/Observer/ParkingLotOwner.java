package com.bl.parkinglotsystem.Observer;

import com.bl.parkinglotsystem.ParkingLotSystem;

public class ParkingLotOwner implements ParkingLotObservar {
    //FIELDS
    ParkingLotSystem parkingLotSystem;
    private boolean isParkingFull;

    public ParkingLotOwner() {
        parkingLotSystem = new ParkingLotSystem();
    }

    @Override
    public void updateParkingStatus(boolean parkingStatus) {
        isParkingFull = parkingStatus;
    }
}
