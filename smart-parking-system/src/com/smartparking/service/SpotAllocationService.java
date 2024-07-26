package com.smartparking.service;

import java.util.List;
import java.util.Optional;

import com.smartparking.model.ParkingSpot;
import com.smartparking.model.VehicleType;

public class SpotAllocationService {
	public Optional<ParkingSpot> findAvailableSpot(List<ParkingSpot> spots, VehicleType vehicleType) {
		return spots.stream().filter(spot -> !spot.isOccupied() && spot.getSuitableFor() == vehicleType).findFirst();
	}
}
