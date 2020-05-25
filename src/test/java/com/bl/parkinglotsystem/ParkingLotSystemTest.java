package com.bl.parkinglotsystem;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        boolean isParked = parkingLotSystem.park(new Object());
        Assert.assertTrue(isParked);
    }
}