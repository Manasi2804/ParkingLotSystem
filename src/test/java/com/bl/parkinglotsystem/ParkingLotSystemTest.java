package com.bl.parkinglotsystem;
import com.bl.parkinglotsystem.exception.ParkingLotSystemException;
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
    public void givenAVehicle_WhenParked_ReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.ParkingLotSystem(1);
        boolean isParked = parkingLotSystem.park(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldNotBeParked() {
        parkingLotSystem.ParkingLotSystem(2);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_FULL, e.type);
        }
    }

    @Test
    public void givenVehicle_WhenUnParked_ShouldUnpark() throws ParkingLotSystemException {
        parkingLotSystem.ParkingLotSystem(1);
        parkingLotSystem.park(vehicle);
        Object isUnParked = parkingLotSystem.unPark(vehicle);
        Assert.assertEquals(vehicle, isUnParked);
    }

    @Test
    public void givenVehicle_WhenAlreadyUnParked_ShouldReturnFalse() {
        parkingLotSystem.ParkingLotSystem(1);
        try {
            parkingLotSystem.park(vehicle);
            Object isUnParked = parkingLotSystem.unPark(new Object());
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NO_VEHICLE, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenParkingLotIsFull_thenShouldNotifyToOwner() throws ParkingLotSystemException {
        Owner owner = new Owner();
        parkingLotSystem.ParkingLotSystem(2, owner);
        Object One = new Object();
        Object Two = new Object();
        parkingLotSystem.park(One);
        parkingLotSystem.park(Two);
        Assert.assertEquals("Parking lot is full", owner.getMessage());

    }
}