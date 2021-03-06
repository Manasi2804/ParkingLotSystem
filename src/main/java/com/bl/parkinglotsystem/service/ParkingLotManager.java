package com.bl.parkinglotsystem.service;

import com.bl.parkinglotsystem.Observer.ParkingLotObservar;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotManager implements ParkingInformer {
    private List<ParkingLotObservar> lotObservers;

    public ParkingLotManager() {
        lotObservers = new ArrayList<>();
    }
    @Override
    public void addObserver(ParkingLotObservar lotObserver) {
        lotObservers.add(lotObserver);
    }
    @Override
    public void notifyParkingStatus(boolean parkingStatus) {
        for (ParkingLotObservar lotObserver : lotObservers) {
            lotObserver.updateParkingStatus(parkingStatus);
        }
    }
}
