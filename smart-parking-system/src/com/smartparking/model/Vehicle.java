package com.smartparking.model;

public class Vehicle {
	private String licensePlate;
	private VehicleType type;

	public Vehicle(String licensePlate, VehicleType type) {
		this.licensePlate = licensePlate;
		this.type = type;
	}

	// Getters and setters
	public String getLicensePlate() {
		return licensePlate;
	}

	public VehicleType getType() {
		return type;
	}
}
