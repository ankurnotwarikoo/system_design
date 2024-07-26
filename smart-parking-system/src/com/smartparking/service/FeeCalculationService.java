package com.smartparking.service;

import com.smartparking.model.ParkingTransaction;

public class FeeCalculationService {
	private FeeCalculationStrategy strategy;

	public FeeCalculationService(FeeCalculationStrategy strategy) {
		this.strategy = strategy;
	}

	public void setStrategy(FeeCalculationStrategy strategy) {
		this.strategy = strategy;
	}

	public double calculateFee(ParkingTransaction transaction) {
		return strategy.calculateFee(transaction);
	}
}
