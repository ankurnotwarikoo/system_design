package com.smartparking.service;

import com.smartparking.model.ParkingTransaction;

public interface FeeCalculationStrategy {
	double calculateFee(ParkingTransaction transaction);
}
