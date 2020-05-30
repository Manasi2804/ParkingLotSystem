/*****************************************************************************************
 * @purpose: ParkingLot System Class Define the the function of park,unpark the Vehicle
 * @Author: Manasi
 * @Date: 30/5/2020
 *****************************************************************************************/
package com.bl.parkinglotsystem;

import com.bl.parkinglotsystem.Observer.AirportSecurity;
import com.bl.parkinglotsystem.Observer.ParkingLotOwner;
import com.bl.parkinglotsystem.exception.ParkingLotSystemException;
import com.bl.parkinglotsystem.service.ParkingLotManager;
import java.util.HashMap;
import java.util.Map;

public class ParkingLotSystem {

    int NUMBER_OF_PARKING_LOTS;
    int SIZE_OF_PARKING_LOT;
    ParkingLotManager parkingLotManager;
    ParkingLotOwner parkingLotOwner;
    AirportSecurity airportSecurity;
    ParkingAttendant parkingAttendant;
    Map<String, Vehicle> vehicleMap;

    public ParkingLotSystem(int NUMBER_OF_PARKING_LOTS, int SIZE_OF_PARKING_LOT) {
        this.SIZE_OF_PARKING_LOT = SIZE_OF_PARKING_LOT;
        this.NUMBER_OF_PARKING_LOTS = NUMBER_OF_PARKING_LOTS;
        parkingLotManager = new ParkingLotManager();
        parkingLotOwner = new ParkingLotOwner();
        airportSecurity = new AirportSecurity();
        parkingLotManager.addObserver(parkingLotOwner);
        parkingLotManager.addObserver(airportSecurity);
        parkingAttendant = new ParkingAttendant(this);
        vehicleMap = new HashMap<>();
    }

    public ParkingLotSystem() {
    }

    /******
     * @purpose: TO PARK VEHICLE
     ******/
    public void park(Vehicle vehicle)  {
        if (isLotFull()) {
            parkingLotManager.notifyParkingStatus(true);
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking is full");
        }
        parkingAttendant.parkVehicle(vehicle);
    }

    /*******
     * @purpose: TO CHECK LOT IS FULL OR NOT
     ******/
    public boolean isLotFull() {
        return vehicleMap.size() == SIZE_OF_PARKING_LOT * NUMBER_OF_PARKING_LOTS;
    }

    /******
     * @purpose: TO UNPARK VEHICLE
     */
    public void unPark(Vehicle vehicle)  {
        if (!isVehicleParked(vehicle)) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NOT_PARKED_HERE, "NO vehicle");
        }
        parkingAttendant.unParkedVehicle(vehicle);
        parkingLotManager.notifyParkingStatus(false);
    }

    /*******
     * @purpose: TO CHECK VEHICLE PARKED OR NOT
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        return vehicleMap.containsValue(vehicle);
    }

    /*********
     * @purpose: TO CHECK VEHICLE UNPARKED
     */
    public boolean isVehicleUnPark(Vehicle vehicle) {
        return !isVehicleParked(vehicle);
    }

    /*****
     * @purpose: TO GET VEHICLE POSITION
     */
    public String getVehiclePosition(Vehicle vehicle) {
        return parkingAttendant.getVehiclePosition(vehicle);
    }

    /**********
     * @purpose: TO GET NUMBER OF VEHICLES PARKED IN A PARKING LOT
     */
    public int getNumberOfVehiclesParked(int parkingLotNumber) {
        return (int) vehicleMap.keySet().stream().filter(key -> key.contains("A" + parkingLotNumber)).count();
    }
}