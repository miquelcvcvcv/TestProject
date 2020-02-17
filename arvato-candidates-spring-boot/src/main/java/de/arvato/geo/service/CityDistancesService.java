package de.arvato.geo.service;

import java.util.Collections;
import java.util.List;

import de.arvato.geo.domain.City;
import de.arvato.geo.repository.CityDistancesRepository;
import de.arvato.geo.repository.CityRepository;

public class CityDistancesService {

	private CityDistancesRepository cityDistancesRepository;
	
	
	
	public CityDistancesService(CityDistancesRepository cityDistancesRepository) {
		this.cityDistancesRepository=cityDistancesRepository;
	}
	
	
	public Iterable<City> list(){
		return cityDistancesRepository.findAll();
	}
	
	
	
	public Iterable<City> listaordenadaalfabeticamente(){
				
		List<City> cities=(List<City>) cityDistancesRepository.findAll();
		Collections.sort(cities);
		return cities;
			
	}
	
	public long numerodeciudades(){
		
		
		return 1;
		//return cityDistanceRepository.count();
	}

	
	public City saveAll (City city)
	 {
		 return cityDistancesRepository.save(city);
	 }
	
	
	 
	 
	 
	 
	public Iterable<City> saveAll(List<City> cities) {
		return cityDistancesRepository.saveAll(cities);
		 
		
		
	}
	
}
