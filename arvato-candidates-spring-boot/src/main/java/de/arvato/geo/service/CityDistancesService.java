package de.arvato.geo.service;

import java.util.Collections;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.arvato.geo.domain.Boolint;
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
	
	
 
	public Iterable<City> saveAllcities(List<City> cities) {
		
		return cityService.saveAll(cities);
				
		
	}
	
	/////////////////////////////DISTANCES////////////////////////
	
	
	public Iterable<Distances> listDistances(){
		return distanceService.list();
	}
	
	public Iterable<Distances> listadistanciasordenadaalfabeticamente(){
		
		return distanceService.listaordenadaalfabeticamente();
		 
	}
	
	
	 public Distances saveAllDistances (Distances distances)
	 {
		 return distanceService.saveAll(distances);
	 }
	
	
	 
	public Iterable<Distances> saveAllDistances(List<Distances> distances) {
		return distanceService.saveAll(distances);
		 //cityRepository.saveAll(cities);
		
		
	}
	
	public  String distanciaEntreciudades(int startLatGrados,int startLatMinutos,double startLatSegundos,String startLatOrientacion,int startLonGrados,int startLonMinutos,double startLonSegundos,String startLonOrientacion, int endLatGrados,int endLatMinutos, double endLatSegundos, String endLatOrientacion, int endLonGrados,int endLonMinutos, double endLonSegundos, String endLonOrientacion, boolean peaje)
		
	  {
		
		
		String s="DISTANCIA ENTRE DOS CIUDADES "+"<br></br>" ;
		
		s=s+"Distancia calculada por geoUtils"+"<br></br>";;
		//peaje=false;
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
			
				
		 double km2=GeoUtils.getDistance(startlatorigin, startlonorigin, endlatorigin, endlonorigin);
		 
		 s=s+"Distancia calculada entre ciudades despues de convertir de gsm a lat y lon por get Distance"+"<br></br>";
		 s=s+km2+"<br></br>";
		 
		 
		 s=s+"Busqueda de la ciudad de origen usando lat y lon. LAT="+startlatorigin+" LON="+startlonorigin+"<br></br>";
		 Boolint biorigen;
		 biorigen=this.buscar_ciudad_en_lista_ciudades(startlatorigin, startlonorigin);
		List<City> cities=(List<City>)cityService.listaordenadaalfabeticamente();
		
		 if (biorigen.isB())
		 {
			 
			 s=s+"Ciudad origen encontrada: ";
			 s=s+cities.get(biorigen.getI()).getName()+"<br></br>";
				
		 }else
		 {
			 s=s+"Ciudad origen NO encontrada: "+"<br></br>";
			 
			
		 }
		 
		 s=s+"Busqueda de la ciudad de destno usando lat y lon. LAT="+endlatorigin+" LON="+endlonorigin+"<br></br>";
		 Boolint bidestino;
		 bidestino=this.buscar_ciudad_en_lista_ciudades(endlatorigin, endlonorigin);
		 
		 if (bidestino.isB())
		 {
			 
			 s=s+"Ciudad destino encontrada: ";
			 s=s+cities.get(bidestino.getI()).getName()+"<br></br>";
				
		 }else
		 {
			 s=s+"Ciudad destino NO encontrada: "+"<br></br>";
			 			
		 }
		 
		 //si ha encontrado la ciudad de origen se procede a buscar rutas
		 if (biorigen.isB()&&bidestino.isB())
		 {
			 Boolint bibs=new Boolint();
		  //busqueda de una ruta simple de las dos ciudades en la lista de distancias
			 bibs=this.busquedasimplederutas(cities.get(biorigen.getI()).getName(), cities.get(bidestino.getI()).getName()) ;
			 //Ruta simple encontrada
			 if (bibs.isB())
			 {
				 List<Distances> distances=(List<Distances>)distanceService.listaordenadaalfabeticamente();
				 s=s+"Ruta simple encontrada: "+"<br></br>";
				 if (peaje)
				 {
				 s=s+"Distancia con peajes: "+distances.get(bibs.getI()).getDistance().getToll()+"<br></br>";
				 }
				 else
				 {
				 s=s+"Distancia sin peajes: "+distances.get(bibs.getI()).getDistance().getfree()+"<br></br>";
				 }
			 }else
			 {
				 s=s+"Ruta simple NO encontrada: "+"<br></br>";
				 
			 }
			 
		 }
		 
		
		 
		 
		 
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
	
	private Boolint buscar_ciudad_en_lista_ciudades(double lat, double lon)
	{
		Boolint bi=new Boolint();
	
		double lattwodeciamls, lontwodecimals,latitwodeciamls, lonitwodecimals;
		
		//redondeo lat i lon a 2 decimales 
		lattwodeciamls=Math.rint(lat*100)/100;
		lontwodecimals=Math.rint(lon*100)/100;
		
		
		
		
		//System.out.println(num);
	    int i=0;
		int numero_maximo_ciudades=(int)cityService.numerodeciudades();
		List<City> cities=(List<City>)cityService.listaordenadaalfabeticamente();
		boolean bciudad_encontrada=false;
		while (( i<numero_maximo_ciudades)&&(bciudad_encontrada==false))
			{
			latitwodeciamls=Math.rint(cities.get(i).getLat()*100)/100;
			lonitwodecimals=Math.rint(cities.get(i).getLon()*100)/100;
			
			if ((latitwodeciamls==lattwodeciamls)&&(lonitwodecimals==lontwodecimals))
			{
				bi.setI(i);
				bi.setB(true);
				bciudad_encontrada=true;	
				
			}
			i++;
			}	
		
		return bi;
	}
	private Boolint busquedasimplederutas(String ciudadorigen, String ciudaddestino)
	{
		Boolint bi=new Boolint();
		//List<Distances> distances=(List<Distances>)distanceService.list();
		List<Distances> distances=(List<Distances>)distanceService.listaordenadaalfabeticamente();
		
		int i=0;
		while (i<distances.size() )
		{
			if (ciudadorigen.equals(distances.get(i).getOrigin()))
			{
				if (distances.get(i).getdestination().equals(ciudaddestino))
				{
				bi.setB(true);
				bi.setI(i);
				}
			}
			
			i++;
		}
		
		return bi;
	}
	

}
