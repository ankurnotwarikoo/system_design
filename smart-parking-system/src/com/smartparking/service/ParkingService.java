package com.smartparking.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.smartparking.model.ParkingSpot;
import com.smartparking.model.ParkingTransaction;
import com.smartparking.model.Vehicle;
import com.smartparking.model.VehicleType;
import com.smartparking.util.ConcurrencyUtil;

public class ParkingService {
	private final List<ParkingSpot> parkingSpots = new ArrayList<>();
	private final Map<String, ParkingTransaction> activeTransactions = new HashMap<>();
	private final List<ParkingTransaction> completedTransactions = new ArrayList<>();
	private int nextTransactionId = 1;

	private final SpotAllocationService spotAllocationService;
	private final FeeCalculationService feeCalculationService;

	public ParkingService(SpotAllocationService spotAllocationService, FeeCalculationService feeCalculationService) {
		this.spotAllocationService = spotAllocationService;
		this.feeCalculationService = feeCalculationService;
	}

	public void initializeParkingLot(int motorcycleSpots, int carSpots, int busSpots) {
		int id = 1;
		for (int i = 0; i < motorcycleSpots; i++) {
			parkingSpots.add(new ParkingSpot(id++, VehicleType.MOTORCYCLE));
		}
		for (int i = 0; i < carSpots; i++) {
			parkingSpots.add(new ParkingSpot(id++, VehicleType.CAR));
		}
		for (int i = 0; i < busSpots; i++) {
			parkingSpots.add(new ParkingSpot(id++, VehicleType.BUS));
		}
	}

	public ParkingTransaction parkVehicle(Vehicle vehicle) {
		return ConcurrencyUtil.performAtomicOperation(() -> {
			Optional<ParkingSpot> spot = spotAllocationService.findAvailableSpot(parkingSpots, vehicle.getType());
			if (spot.isPresent()) {
				ParkingSpot parkingSpot = spot.get();
				parkingSpot.setOccupied(true);
				ParkingTransaction transaction = new ParkingTransaction(nextTransactionId++, vehicle, parkingSpot);
				activeTransactions.put(vehicle.getLicensePlate(), transaction);
				return transaction;
			}
			throw new RuntimeException("No available parking spot");
		});
	}

	public ParkingTransaction exitVehicle(String licensePlate) {
		return ConcurrencyUtil.performAtomicOperation(() -> {
			ParkingTransaction transaction = activeTransactions.remove(licensePlate);
			if (transaction == null) {
				throw new RuntimeException("No active transaction found for license plate: " + licensePlate);
			}
			transaction.setExitTime(LocalDateTime.now());
			transaction.setFee(feeCalculationService.calculateFee(transaction));
			transaction.getSpot().setOccupied(false);
			completedTransactions.add(transaction);
			return transaction;
		});
	}

	public List<ParkingSpot> getParkingSpots() {
		return new ArrayList<>(parkingSpots);
	}

	public Map<String, ParkingTransaction> getActiveTransactions() {
		return new HashMap<>(activeTransactions);
	}

	public List<ParkingTransaction> getCompletedTransactions() {
		return new ArrayList<>(completedTransactions);
	}
}
