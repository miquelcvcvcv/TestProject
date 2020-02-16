package de.arvato.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.arvato.geo.utils.GeoUtils;
import de.arvato.geo.utils.GeoUtils.O;

public class GeoUtilsTest {

	@Test
	public void testGetDecimalValue() {
		assertEquals(41.509007, GeoUtils.getDecimalValue(41, 30, 32.4252, O.N), 0.0001);
		assertEquals(1.875759, GeoUtils.getDecimalValue(1, 52, 32.7324, O.E), 0.0001);
		assertEquals(-84.882907, GeoUtils.getDecimalValue(84, 52, 58.5, O.W), 0.0001);
		assertEquals(-11.654528, GeoUtils.getDecimalValue(11, 39, 16.3, O.S), 0.0001);
	}
	
	@Test
	public void testGetDistance() {
		assertEquals(20.67, GeoUtils.getDistance(41.612190, 2.005440, 41.652594, 2.248259), 0.01);
		assertEquals(255.79, GeoUtils.getDistance(41.386604, 2.167277, 41.645445, -0.885613), 0.01);
	}

}