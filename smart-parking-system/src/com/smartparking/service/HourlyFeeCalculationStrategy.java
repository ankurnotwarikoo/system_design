package com.smartparking.service;

import java.time.temporal.ChronoUnit;

import com.smartparking.model.ParkingTransaction;

public class HourlyFeeCalculationStrategy implements FeeCalculationStrategy {
	private static final double HOURLY_RATE = 5.0;

	@Override
	public double calculateFee(ParkingTransaction transaction) {
		long hours = ChronoUnit.HOURS.between(transaction.getEntryTime(), transaction.getExitTime());
		return Math.max(HOURLY_RATE, hours * HOURLY_RATE); // Minimum fee is HOURLY_RATE
	}
}
