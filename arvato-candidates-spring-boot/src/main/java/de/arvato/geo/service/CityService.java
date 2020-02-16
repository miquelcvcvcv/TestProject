package de.arvato.geo.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import de.arvato.geo.domain.City;
import de.arvato.geo.domain.Distances;
import de.arvato.geo.repository.CityRepository;
import de.arvato.geo.utils.GeoUtils;

@Service
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
		
		//cityRepository.
		//cityRepository.findAll();
		
		//cityRepository.count(); 
		
		return cityRepository.count();
	}

	
	public Iterable<City> listaordenadaalfabeticamente(){
		
		
		//java.util.Collection.sort(cityRepository);
		//Collections.sort((List<City>) cityRepository.findAll()).findAll();
		List<City> cities=(List<City>) cityRepository.findAll();
		Collections.sort(cities);
		return cities;
		 
		 //return  Collections.sort((List<City>) cityRepository.findAll()).findAll();
		//return cityRepository.findAll();
	}


	
	
	 public City saveAll (City city)
	 {
		 return cityRepository.save(city);
	 }
	
	
	 
	 
	 
	 
	 
	public Iterable<City> saveAll(List<City> cities) {
		return cityRepository.saveAll(cities);
		 //cityRepository.saveAll(cities);
		
		
	}
	
	
	/*public String getDistance(algooog)
	{
		Ciudad c1 = algo.getCity1();
		Ciudad c2 = algo.getCity2();
	}
	*/
	
	/*private CitiesRepository citiesRepository;
	
	private CitiesRepository (citiesRepository citiesRepository)
	{
		this.citiesRepository =citiesRepository
		
	}
	*/
	
}
