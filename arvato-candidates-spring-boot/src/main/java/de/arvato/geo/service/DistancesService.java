package de.arvato.geo.service;

import java.util.Collections;

import java.util.List;

//import org.jboss.logging.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;


import de.arvato.geo.domain.Distances;
import de.arvato.geo.repository.DistancesRepository;
import de.arvato.geo.utils.GeoUtils;
import de.arvato.geo.utils.GeoUtils.O;



@Component
//@Service
public class DistancesService {



	
	//@Autowired
	 //GeoUtils geo;
	//private static final Logger LOGGER= Logger.getLogger(Service.class);

	
	
	
	
	private DistancesRepository distancesRepository;
	
	
	public DistancesService(DistancesRepository distancesRepository) {
		this.distancesRepository=distancesRepository;
	}
	
	
	public Iterable<Distances> list(){
		return distancesRepository.findAll();
	}
	
	public Iterable<Distances> listaordenadaalfabeticamente(){
		
		
		List<Distances> distances=(List<Distances>) distancesRepository.findAll();
		Collections.sort(distances);
		return distances;
		 
	}
	
	 public Distances saveAll (Distances distances)
	 {
		 return distancesRepository.save(distances);
	 }
	
	
	 
	public Iterable<Distances> saveAll(List<Distances> distances) {
		return distancesRepository.saveAll(distances);
			
		
	}
		
	public  String distanciaentreciudades(int startLatGrados,int startLatMinutos,double startLatSegundos,String startLatOrientacion,int startLonGrados,int startLonMinutos,double startLonSegundos,String startLonOrientacion, int endLatGrados,int endLatMinutos, double endLatSegundos, String endLatOrientacion, int endLonGrados,int endLonMinutos, double endLonSegundos, String endLonOrientacion, Boolean peaje)
		
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
		
		
		 double startlatorigin=GeoUtils.getDecimalValue(startLatGrados, startLatMinutos, startLatSegundos, startlatorientation);
		 double startlonorigin=GeoUtils.getDecimalValue(startLonGrados, startLonMinutos, startLonSegundos, startlonorientation);
		
		 double endlatorigin=GeoUtils.getDecimalValue(endLatGrados, endLatMinutos, endLatSegundos, endlatorientation);
		 double endlonorigin=GeoUtils.getDecimalValue(endLonGrados, endLonMinutos, endLonSegundos, endlonorientation);
			
		 
		 double km=GeoUtils.getDistance(41.3828939, 2.1774322,  41.1172364, 1.2546057);
		
		//double km=0;
		 s=s+km +"<br></br>";
		
		 double km2=GeoUtils.getDistance(startlatorigin, startlonorigin, endlatorigin, endlonorigin);
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
