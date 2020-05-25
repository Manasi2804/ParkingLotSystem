package com.bl.parkinglotsystem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    Object vehicle;
    @Before
    public void setUp() {
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Object();
    }
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        boolean isParked = parkingLotSystem.park(new Object());
        Assert.assertTrue(isParked);
    }
    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.unPark(vehicle);
        Assert.assertTrue(isUnParked);
    }
}