package com.smartparking.model;

public class ParkingSpot {
	private int id;
	private boolean isOccupied;
	private VehicleType suitableFor;

	public ParkingSpot(int id, VehicleType suitableFor) {
		this.id = id;
		this.isOccupied = false;
		this.suitableFor = suitableFor;
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean occupied) {
		isOccupied = occupied;
	}

	public VehicleType getSuitableFor() {
		return suitableFor;
	}
}
