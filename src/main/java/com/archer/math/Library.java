package com.archer.math;

import java.util.concurrent.atomic.AtomicBoolean;

final class Library {
	private static final String WIN_LIB = "lib/libmath.dll";
	private static final String LINUX_LIB = "lib/libmath.so";
	
	private static volatile AtomicBoolean libLoaded = new AtomicBoolean(false);
	
	public static void loadMathLib() {
		if(!libLoaded.getAndSet(true)) {
			Constant.loadLib(WIN_LIB, LINUX_LIB);
		}
	} 
}
