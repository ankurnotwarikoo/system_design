package com.smartparking;

import com.smartparking.model.ParkingTransaction;
import com.smartparking.model.Vehicle;
import com.smartparking.model.VehicleType;
import com.smartparking.service.FeeCalculationService;
import com.smartparking.service.HourlyFeeCalculationStrategy;
import com.smartparking.service.ParkingService;
import com.smartparking.service.SpotAllocationService;

public class SmartParkingSystem {
	private static SmartParkingSystem instance;
	private ParkingService parkingService;

	private SmartParkingSystem() {
		SpotAllocationService spotAllocationService = new SpotAllocationService();
		FeeCalculationService feeCalculationService = new FeeCalculationService(new HourlyFeeCalculationStrategy());
		this.parkingService = new ParkingService(spotAllocationService, feeCalculationService);
	}

	public static synchronized SmartParkingSystem getInstance() {
		if (instance == null) {
			instance = new SmartParkingSystem();
		}
		return instance;
	}

	public void initializeSystem() {
		parkingService.initializeParkingLot(10, 30, 10); // 10 motorcycle spots, 30 car spots, 10 bus spots
	}

	public ParkingService getParkingService() {
		return parkingService;
	}

	public static void main(String[] args) {
		SmartParkingSystem system = SmartParkingSystem.getInstance();
		system.initializeSystem();

		// Example usage
		Vehicle car = new Vehicle("ABC123", VehicleType.CAR);
		ParkingTransaction transaction = system.getParkingService().parkVehicle(car);
		System.out.println("Car parked in spot: " + transaction.getSpot().getId());

		// Simulate time passing...
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		transaction = system.getParkingService().exitVehicle("ABC123");
		System.out.println("Car exited. Fee: $" + transaction.getFee());

		// Print parking lot status
		System.out.println("Available spots: "
				+ system.getParkingService().getParkingSpots().stream().filter(spot -> !spot.isOccupied()).count());
		System.out.println("Active transactions: " + system.getParkingService().getActiveTransactions().size());
		System.out.println("Completed transactions: " + system.getParkingService().getCompletedTransactions().size());
	}
}
