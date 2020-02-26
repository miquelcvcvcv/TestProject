package de.arvato.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.internal.runners.InitializationError;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.arvato.geo.controller.CityDistancesController;
import de.arvato.geo.repository.CityDistancesRepository;
import de.arvato.geo.service.CityDistancesService;
import de.arvato.geo.utils.GeoUtils;
import de.arvato.geo.utils.GeoUtils.O;




//@WebMvcTest(EmployeeRestController.class)
@RunWith(SpringRunner.class)
public class ArvatoCandidatesSpringBootApplicationTests2 {

	//@Autowired
	//CityDistancesService cds;
	
	// @Mock
	   // private HelloRepository helloRepository;
	//	private CityDistancesRepository cdr;
	 
	// @InjectMocks 
	   // private HelloService helloService = new HelloServiceImpl();
	// private CityDistancesService cds = new CityDistancesService();

	
	@Test
	public void contextLoads(CityDistancesService cds)  {
	
		
		//CityDistancesService citiesDistancesService;
		
		//citiesDistancesService.saveAllcities(cities);
		//CityDistancesController citydistancescontroller ;
		//citydistancescontroller.numerodeciudades();
	//	CityDistancesService citydistancesservice;
	//	CityDistancesController cdc= new CityDistancesController();
		
		//CityDistancesService cds=new CityDistancesService();
	//float result;
	//	result=cds.numerodeciudades();
		
		//float resultado=cds.numerodeciudades();
		//
		//float result=CityDistancesController.class.newInstance().numerodeciudades();
		
		//System.out.println(resultado);
		
		
		
		//assertEquals(14,resultado);
		//assertEquals(41.509007, GeoUtils.getDecimalValue(41, 30, 32.4252, O.N), 0.0001);
		//assertEquals(41.509007, GeoUtils.getDecimalValue(41, 30, 32.4252, O.N), 0.0001);
		//assertEquals(41.509007, GeoUtils.getDecimalValue(41, 30, 32.4252, O.N), 0.0001);
		
	}

}
