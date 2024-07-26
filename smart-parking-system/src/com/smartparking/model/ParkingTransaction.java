package com.smartparking.model;

import java.time.LocalDateTime;

public class ParkingTransaction {
	private int id;
	private Vehicle vehicle;
	private ParkingSpot spot;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private double fee;

	public ParkingTransaction(int id, Vehicle vehicle, ParkingSpot spot) {
		this.id = id;
		this.vehicle = vehicle;
		this.spot = spot;
		this.entryTime = LocalDateTime.now();
	}

	// Getters and setters
	public int getId() {
		return id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public ParkingSpot getSpot() {
		return spot;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
}
