package com.bl.parkinglotsystem;

import com.bl.parkinglotsystem.Observer.ParkingLotObservar;

public interface ParkingInformer {
    void addObserver(ParkingLotObservar lotObserver);
    void notifyParkingStatus(boolean parkingStatus);
}