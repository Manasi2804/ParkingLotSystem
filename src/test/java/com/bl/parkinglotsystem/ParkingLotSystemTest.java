package com.bl.parkinglotsystem;
import com.bl.parkinglotsystem.Observer.AirportSecurity;
import com.bl.parkinglotsystem.Observer.ParkingLotOwner;
import com.bl.parkinglotsystem.exception.ParkingLotSystemException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    Vehicle vehicle;
    ParkingLotOwner parkingLotOwner;
    AirportSecurity airportSecurity;

    @Before
    public void setUp() {
        parkingLotSystem = new ParkingLotSystem(2, 3);
        parkingLotOwner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenAVehicleToPark_WhenParkedInParkingLot_ShouldReturnTrue() {
        try {
            vehicle = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
            parkingLotSystem.park(vehicle);
            boolean isPark = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isPark);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParkedInParkingLot_ShouldThrowException() {
        parkingLotSystem = new ParkingLotSystem(3, 3);
        try {
            parkingLotSystem.park(new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R"));
            parkingLotSystem.park(new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R"));
            parkingLotSystem.park(new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R"));
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenNotParkedInParkingLot_ShouldReturnFalse() {
        vehicle = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "R");
        boolean isPark = parkingLotSystem.isVehicleParked(vehicle);
        Assert.assertFalse(isPark);
    }

    @Test
    public void givenAVehicle_WhenUnParkedFromParkingLot_ShouldReturnTrue() {
        try {
            Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.unPark(vehicleOne);
            boolean isUnPark = parkingLotSystem.isVehicleUnPark(vehicleOne);
            Assert.assertTrue(isUnPark);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenNoVehicleUnParked_ShouldThrowException() {
        try {
            Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "R");
            Vehicle vehicleTwo = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "R");
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.isVehicleUnPark(vehicleOne);
            parkingLotSystem.unPark(vehicleTwo);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NOT_PARKED_HERE, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenNotUnParked_ShouldReturnFalse() {
        Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "R");
        try {
            parkingLotSystem.park(vehicleOne);
            boolean isUnPark = parkingLotSystem.isVehicleUnPark(vehicleOne);
            Assert.assertFalse(isUnPark);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenThereIsSpace1inParkingLot_ShouldAllowToPark() {
        Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        Vehicle vehicleTwo = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        Vehicle vehicleThree = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.unPark(vehicleTwo);
            parkingLotSystem.park(vehicleThree);
            boolean isPark = parkingLotSystem.isVehicleParked(vehicleThree);
            Assert.assertTrue(isPark);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldThrowException() {
        Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "R");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleOne);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_PARKED, e.type);
        }
    }

    @Test
    public void givenAVehicle_WhenAskForPosition_ShouldReturnPosition() {
        Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        try {
            parkingLotSystem.park(vehicleOne);
            String vehiclePosition = parkingLotSystem.getVehiclePosition(vehicleOne);
            Assert.assertEquals("A1 1", vehiclePosition);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicles_WhenParkedInParkingLot_ShouldEvenlyDistributeInParkingLot() {
        Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "R");
        Vehicle vehicleTwo = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            String vehicle1Position = parkingLotSystem.getVehiclePosition(vehicleOne);
            String vehicle2Position = parkingLotSystem.getVehiclePosition(vehicleTwo);
            Assert.assertEquals("A1 1", vehicle1Position);
            Assert.assertEquals("A2 1", vehicle2Position);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenDriverIsHandicap_ShouldParkVehicleAtNearestLotPosition() {
        Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        Vehicle vehicleTwo = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        Vehicle vehicleThree = new Vehicle( Vehicle.DriverType.HANDICAP, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "R");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.unPark(vehicleOne);
            parkingLotSystem.park(vehicleThree);
            String vehicle1Position = parkingLotSystem.getVehiclePosition(vehicleThree);
            Assert.assertEquals("A1 1", vehicle1Position);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenVehicleOfLargeSize_WhenMaximumNumberOfParkingSlotAvailable_ShouldParkedInThatParkingLot() {
        Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        Vehicle vehicleTwo = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        Vehicle vehicleThree = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "R");
        Vehicle vehicleFour = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.park(vehicleThree);
            parkingLotSystem.park(vehicleFour);
            String vehicleFourPosition = parkingLotSystem.getVehiclePosition(vehicleFour);
            Assert.assertEquals("A2 2", vehicleFourPosition);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void givenParkingLotMap_WhenNeedInformationAsPerVehicleColour_ShouldReturnVehicleMapWithRespectiveColour() {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        Vehicle vehicleTwo = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "R");
        Vehicle vehicleThree = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL, Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.BMW, "R");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            parkingLotSystem.park(vehicleThree);
            Map<String, Vehicle> vehiclesOfColour = policeDepartment.getVehicles(Vehicle.VehicleColour.WHITE);
            Assert.assertEquals(3, vehiclesOfColour.size());
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenVehicleColorAndBrandMatch_ShouldReturnVehicleListWithColorBlueAndModelToyota() {
        PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
        Vehicle vehicleOne = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.TOYOTA, "A");
        Vehicle vehicleTwo = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.LARGE,
                Vehicle.VehicleColour.WHITE, Vehicle.VehicleModel.TOYOTA, "B");
        Vehicle vehicleThree = new Vehicle( Vehicle.DriverType.NORMAL, Vehicle.VehicleSize.SMALL,
                Vehicle.VehicleColour.BLUE, Vehicle.VehicleModel.BMW, "C");
        try {
            parkingLotSystem.park(vehicleOne);
            parkingLotSystem.park(vehicleTwo);
            Map<String, Vehicle> vehiclesList = policeDepartment.getVehiclesWithColorAndModel(Vehicle.VehicleColour.BLUE,
                    Vehicle.VehicleModel.TOYOTA);
            Assert.assertEquals(1, vehiclesList.size());
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }
}
