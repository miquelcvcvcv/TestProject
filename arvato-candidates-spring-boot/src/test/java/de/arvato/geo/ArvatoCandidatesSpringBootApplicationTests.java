package de.arvato.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.arvato.geo.controller.CityDistancesController;
import de.arvato.geo.utils.GeoUtils;
import de.arvato.geo.utils.GeoUtils.O;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArvatoCandidatesSpringBootApplicationTests {

	@Test
	public void contextLoads() throws InstantiationException, IllegalAccessException {
		assertEquals(14,CityDistancesController.class.newInstance().numerodeciudades());
		//assertEquals(41.509007, GeoUtils.getDecimalValue(41, 30, 32.4252, O.N), 0.0001);
		//assertEquals(41.509007, GeoUtils.getDecimalValue(41, 30, 32.4252, O.N), 0.0001);
		//assertEquals(41.509007, GeoUtils.getDecimalValue(41, 30, 32.4252, O.N), 0.0001);
		
	}

}
