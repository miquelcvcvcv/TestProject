package de.arvato.geo.utils;

import org.springframework.stereotype.Component;

/**
 * 
 * Utilities to manage gps coordinates.
 * @author Arvato Team
 *
 */
@Component
public class GeoUtils {
	
	public enum O {
		N, S, E, W
	};

	private static final int EARTH_RADIUS = 6371; // Approx Earth radius in KM

	// Extracted from https://github.com/jasonwinn/haversine/blob/master/Haversine.java
	// Get distance in KM between two points using the Haversine formula
	public static double getDistance(double startLat, double startLong, double endLat, double endLong) {

		double dLat = Math.toRadians((endLat - startLat));
		double dLong = Math.toRadians((endLong - startLong));

		startLat = Math.toRadians(startLat);
		endLat = Math.toRadians(endLat);

		double a = haversin(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversin(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return EARTH_RADIUS * c; // <-- d
	}
	
	private static double haversin(double val) {
		return Math.pow(Math.sin(val / 2), 2);
	}
	
	public static double getDecimalValue(int degrees, int minutes, double seconds, O orientation) {
		double value = Math.signum(degrees) * (Math.abs(degrees) + (minutes / 60.0) + (seconds / 3600.0));
		if (orientation == O.S || orientation == O.W) {
			
			return -value;
		} else {
			return value;
		}
	}
}