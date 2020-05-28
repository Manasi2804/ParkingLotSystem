package com.bl.parkinglotsystem;
import com.bl.parkinglotsystem.exception.ParkingLotSystemException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    Object vehicle;
    private SlotAllotment slotAllotment;

    @Before
    public void setUp() {
        vehicle = new Object();
        this.slotAllotment = new SlotAllotment(2);
        parkingLotSystem = new ParkingLotSystem(2);
    }
    @Test
    public void givenVehicle_WhenParked_ReturnTrue() {
        try {
            parkingLotSystem.parkTheCar(vehicle);
            boolean isParked = parkingLotSystem.isThisCarPresentInTheParkingLot(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenVehicle_WhenUnParked_ShouldReturnFalse() {
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
        public void givenVehicle_IfRePark_ShouldThrowAnException() {
            try {
                parkingLotSystem.setParkingLotCapacity(3);
                parkingLotSystem.parkTheCar(vehicle);
                parkingLotSystem.parkTheCar(vehicle);
            } catch (ParkingLotSystemException e) {
                e.printStackTrace();
                Assert.assertEquals(ParkingLotSystemException.ExceptionType.CAR_ALREADY_PARKED, e.type);
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
            Assert.assertTrue(ParkingLotObserver.AIRPORT_SECURITY.isParkingFull);
        }
    }
    @Test
    public void givenObserver_WhenParkingIsAvailable_ShouldInformEveryone() {
        ObserversInformer informer = new ObserversInformer();
        informer.informThatParkingIsFull();
        informer.informThatParkingIsAvailable();
        Assert.assertFalse(ParkingLotObserver.AIRPORT_SECURITY.isParkingFull);
    }
    @Test
    public void givenNoVehiclesParked_ShouldReturnUnoccupiedList() {
        int slotsAvailable = this.slotAllotment.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).size();
        Assert.assertEquals(2, slotsAvailable);
    }
    @Test
    public void givenVehicleToPark_InAnEmptyOccupiedList_ShouldReturnSize1() {
        slotAllotment.parkUpdate(vehicle, 1);
        Assert.assertEquals(1, slotAllotment.parkingAvailabilityStatus.get(Availability.OCCUPIED).size());
        Assert.assertEquals(1, slotAllotment.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).size());
    }
    @Test
    public void givenVehicleToUnPark_InAnOccupiedListWhichHas1Car_AfterUnParkShouldReturnSize0() {
        slotAllotment.parkUpdate(vehicle, 1);
        Assert.assertEquals(1, slotAllotment.parkingAvailabilityStatus.get(Availability.OCCUPIED).size());
        Assert.assertEquals(1, slotAllotment.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).size());
        slotAllotment.unParkUpdate(vehicle);
        Assert.assertEquals(0, slotAllotment.parkingAvailabilityStatus.get(Availability.OCCUPIED).size());
        Assert.assertEquals(2, slotAllotment.parkingAvailabilityStatus.get(Availability.UNOCCUPIED).size());
    }
}

