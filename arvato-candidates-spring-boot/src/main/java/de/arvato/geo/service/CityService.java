package de.arvato.geo.service;


import java.util.Collections;
import java.util.List;


import org.springframework.stereotype.Component;


import de.arvato.geo.domain.City;

import de.arvato.geo.repository.CityRepository;


@Component
//@Service
public class CityService {

	//private static final Logger LOGGER= Logger.getLogger(Service.class);
	


	private CityRepository cityRepository;
	
	public CityService(CityRepository cityRepository) {
		this.cityRepository=cityRepository;
	}
	
	
	public Iterable<City> list(){
		return cityRepository.findAll();
	}
	
	public long numerodeciudades(){
		
			
		return cityRepository.count();
	}

	
	public Iterable<City> listaordenadaalfabeticamente(){
		
		
		List<City> cities=(List<City>) cityRepository.findAll();
		Collections.sort(cities);
		return cities;
		 
	}



	 public City saveAll (City city)
	 {
		 return cityRepository.save(city);
	 }
	
	
 
	public Iterable<City> saveAll(List<City> cities) {
		return cityRepository.saveAll(cities);
		
		
		
	}
	
	
	
}
