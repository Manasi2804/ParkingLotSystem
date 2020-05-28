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
        parkingLotSystem.setParkingLotCapacity(2);
    }
    @Test
    public void givenVehicle_WhenParked_ReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.parkTheCar(vehicle);
        boolean isParked = parkingLotSystem.isThisCarPresentInTheParkingLot(vehicle);
        Assert.assertTrue(isParked);
    }
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.parkTheCar(vehicle);
            parkingLotSystem.unParkTheCar(vehicle);
            boolean isUnParked = parkingLotSystem.isThisCarPresentInTheParkingLot(vehicle);
            Assert.assertFalse(isUnParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenVehicle_WhenAlreadyUnParked_ShouldReturnFalse() {
        try {
            parkingLotSystem.parkTheCar(vehicle);
            Object vehicle2 = new Object();
            parkingLotSystem.unParkTheCar(vehicle2);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NO_VEHICLE, e.type);
        }
    }
    @Test
    public void givenParkingLot_WhenFull_ShouldInformToOwner() {
        try {
            parkingLotSystem.parkTheCar(vehicle);
            Object vehicle2 = new Object();
            parkingLotSystem.parkTheCar(vehicle2);
            Object vehicle3 = new Object();
            parkingLotSystem.parkTheCar(vehicle3);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_FULL, e.type);
            Assert.assertTrue(ParkingLotObserver.OWNER.isParkingFull);
        }
    }
    @Test
    public void givenParkingLot_WhenFull_ShouldInformToAirportSecurity(){
        try {
            parkingLotSystem.parkTheCar(vehicle);
            Object vehicle2 = new Object();
            parkingLotSystem.parkTheCar(vehicle2);
            Object vehicle3 = new Object();
            parkingLotSystem.parkTheCar(vehicle3);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_FULL, e.type);
            Assert.assertTrue(ParkingLotObserver.AIRPORT_SECURITY.isParkingFull);
        }
    }
    @Test
    public void givenObserver_WhenParkingIsAvailable_ShouldInformEveryone() {
        ObserversInformer informer = new ObserversInformer();
        informer.informThatParkingIsFull();
        informer.informThatParkingIsAvailable();
        Assert.assertFalse(ParkingLotObserver.OWNER.isParkingFull);
        Assert.assertFalse(ParkingLotObserver.AIRPORT_SECURITY.isParkingFull);
    }
}

