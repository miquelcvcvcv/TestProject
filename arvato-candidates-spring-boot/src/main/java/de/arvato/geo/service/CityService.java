package de.arvato.geo.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import de.arvato.geo.domain.City;
import de.arvato.geo.domain.Distances;
import de.arvato.geo.repository.CityRepository;
import de.arvato.geo.utils.GeoUtils;

@Component
//@Service
public class CityService {

	private static final Logger LOGGER= Logger.getLogger(Service.class);
	
	//@Autowired
	//GeoUtils geo;

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
