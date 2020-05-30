package com.bl.parkinglotsystem;

import com.bl.parkinglotsystem.exception.ParkingLotSystemException;

public class ParkingAttendant {
    ParkingLotSystem parkingLotSystem;
    int lot;

    public ParkingAttendant(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    /********
     * @purpose: TO PARK THE VEHICLE IN PARKING LOT
     */
    public void parkVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (parkingLotSystem.isVehicleParked(vehicle)) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_PARKED, "Vehicle is already parked");
        }
        String key = getParkingPosition();
        parkingLotSystem.vehicleMap.put(key, vehicle);
    }

    /******
     * @purpose: TO UNPARK THE VEHICLE FROM PARKING LOT
     */
    public void unParkedVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (parkingLotSystem.vehicleMap.containsValue(vehicle))
            parkingLotSystem.vehicleMap.remove(getVehiclePosition(vehicle), vehicle);
        else
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NOT_PARKED_HERE, "VEHICLE_NOT_PARKED_HERE");
    }

    /*********
     * @purpose: TO GET THE POSITION IF VEHICLE IN PARKING LOT
     */
    public String getVehiclePosition(Vehicle vehicle) {
        return parkingLotSystem.vehicleMap.keySet().stream()
                .filter(key -> vehicle.equals(parkingLotSystem.vehicleMap.get(key)))
                .findFirst()
                .get();
    }

    /*********
     * @purpose: TO GENERATE AND GET PARKING LOT POSITION
     */
    public String getParkingPosition() {
        String position = null;
        while (lot++ <= parkingLotSystem.NUMBER_OF_PARKING_LOTS) {
            for (int index = 1; index <= parkingLotSystem.SIZE_OF_PARKING_LOT; index++) {
                String key = "A".concat(lot + " " + index);
                if (!parkingLotSystem.vehicleMap.containsKey(key)) {
                    position = key;
                    break;
                }
            }
            if (lot == parkingLotSystem.NUMBER_OF_PARKING_LOTS)
                lot = 0;
            break;
        }
        return position;
    }
}