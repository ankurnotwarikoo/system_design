package com.smartparking.util;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class ConcurrencyUtil {
	private static final ReentrantLock lock = new ReentrantLock();

	public static <T> T performAtomicOperation(Supplier<T> operation) {
		lock.lock();
		try {
			return operation.get();
		} finally {
			lock.unlock();
		}
	}
}
