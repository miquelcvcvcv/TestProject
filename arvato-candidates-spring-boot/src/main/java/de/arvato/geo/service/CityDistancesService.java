package de.arvato.geo.service;

import java.util.Collections;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.arvato.geo.domain.City;
import de.arvato.geo.domain.CityDistances;
import de.arvato.geo.domain.Distances;
import de.arvato.geo.repository.CityDistancesRepository;
import de.arvato.geo.repository.CityRepository;
import de.arvato.geo.repository.DistancesRepository;
import de.arvato.geo.utils.GeoUtils;
import de.arvato.geo.utils.GeoUtils.O;
@Service
public class CityDistancesService {
	
	private static final Logger LOGGER= Logger.getLogger(Service.class);

	@Autowired
	GeoUtils geo;
	
	private CityDistancesRepository cityDistancesRepository;
	
	@Autowired
	private CityService cityService;
	@Autowired
	private DistancesService distanceService;
	
	
	
	public CityDistancesService(CityDistancesRepository cityDistancesRepository) {
		this.cityDistancesRepository=cityDistancesRepository;
	}
	
////////////////////////////////////CITY/////////////////////////////////////////////	

	
	public Iterable<City> list(){
		return cityService.list();
	
	}
	
	public long numerodeciudades(){
		
			
		return cityService.numerodeciudades();
	}

	
	public Iterable<City> listaordenadaalfabeticamente(){
		
		return cityService.listaordenadaalfabeticamente();
		 
	}



	 public City saveAll (City city)
	 {
		 return cityService.saveAll(city);
	 }
	
	
 
	public Iterable<City> saveAlls(List<City> cities) {
		
		return cityService.saveAll(cities);
				
		
	}
	
	/////////////////////////////DISTANCES////////////////////////
	
	
	public Iterable<Distances> listDistances(){
		return distanceService.list();
	}
	
	
	
	 public Distances saveAllDistances (Distances distances)
	 {
		 return distanceService.saveAll(distances);
	 }
	
	
	 
	public Iterable<Distances> saveAllDistances(List<Distances> distances) {
		return distanceService.saveAll(distances);
		 //cityRepository.saveAll(cities);
		
		
	}
	
	public  String distanciaEntreciudades(int startLatGrados,int startLatMinutos,double startLatSegundos,String startLatOrientacion,int startLonGrados,int startLonMinutos,double startLonSegundos,String startLonOrientacion, int endLatGrados,int endLatMinutos, double endLatSegundos, String endLatOrientacion, int endLonGrados,int endLonMinutos, double endLonSegundos, String endLonOrientacion, Boolean peaje)
		
	  {
		
		
		String s="DISTANCIA ENTRE DOS CIUDADES "+"<br></br>" ;
		String ciudad1="Barcelona\n"+"<br></br>";
		String ciudad2="Madrid\n"+"<br></br>";		
		s=s+ciudad1+ciudad2;
		s=s+"Distancia calculada por geoUtils"+"<br></br>";;
		if (peaje==false)
		{
			s=s+"Ruta sin peajes"+"<br></br>";
		}
		//Encontrar ciudad 1;
		O startlatorientation,startlonorientation,endlatorientation,endlonorientation ;
		
		
		startlatorientation=stringtoEnum(startLatOrientacion);
		startlonorientation=stringtoEnum(startLonOrientacion);
		endlatorientation=stringtoEnum(endLatOrientacion);
		endlonorientation=stringtoEnum(endLonOrientacion);
		
		
		 double startlatorigin=geo.getDecimalValue(startLatGrados, startLatMinutos, startLatSegundos, startlatorientation);
		 double startlonorigin=geo.getDecimalValue(startLonGrados, startLonMinutos, startLonSegundos, startlonorientation);
		
		 double endlatorigin=geo.getDecimalValue(endLatGrados, endLatMinutos, endLatSegundos, endlatorientation);
		 double endlonorigin=geo.getDecimalValue(endLonGrados, endLonMinutos, endLonSegundos, endlonorientation);
			
		 
		 double km=geo.getDistance(41.3828939, 2.1774322,  41.1172364, 1.2546057);
		
		//double km=0;
		 s=s+km +"<br></br>";
		
		 double km2=geo.getDistance(startlatorigin, startlonorigin, endlatorigin, endlonorigin);
		 s=s+"Distancia calculada despues de convertir de gsm a lat y lon"+"<br></br>";
		 s=s+km2;
		return s;
		/*
		//encontrar ciudad 1 usando startlatorigin, startlonorigin
		List<City> cities=(List<City>) cityRepository.findAll();
		Collections.sort(cities);
		CityService citiesService;
		citiesService.listaordenadaalfabeticamente();
		*/
		
		
	}

	private O stringtoEnum(String orientacion)
	{
	
	O orientation;
		
	if (orientacion.equalsIgnoreCase(O.S.name()))
	{
		orientation=O.S;
	}
	else if  (orientacion.equalsIgnoreCase(O.N.name()))
	{
		orientation=O.N;
	}
	else if  (orientacion.equalsIgnoreCase(O.E.name()))
	{
		orientation=O.E;
	}
	else 
	{
		orientation=O.W;
	}
	
	return orientation;
	}
	
	
	
	

}
