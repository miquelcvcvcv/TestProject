package de.arvato.geo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.arvato.geo.domain.City;
import de.arvato.geo.domain.Distances;
import de.arvato.geo.service.CityService;
import de.arvato.geo.service.CityDistancesService;

@RestController
@RequestMapping("/citiesanddistances")

public class CityDistancesController {

	private CityDistancesService cityDistancesServices;
	//private CityService cityService;
	
	public CityDistancesController( CityDistancesService  cityDistancesServices)
	{
		this.cityDistancesServices= cityDistancesServices;
	}
/////////////////////////ciudades//////////////////		
@GetMapping("/listcities")
public Iterable<City>list(){
return cityDistancesServices.list();
}
@GetMapping("/listaciudadesordenadaalfabeticamente")
public  Iterable<City> listaordenadaalfabeticamente(){
	return cityDistancesServices.listaordenadaalfabeticamente();
}

@GetMapping("/numerodeciudades")
public long numerodeciudades(){
	return cityDistancesServices.numerodeciudades();
}
//////////////////////////////////////Distancias


@GetMapping("/listdistances")
public Iterable<Distances>listDistance(){
return cityDistancesServices.listDistances();
}

@GetMapping("/listaordenadaalfabeticamente")
public  Iterable<Distances> listadistancasordenadaalfabeticamente(){
	return cityDistancesServices.listadistanciasordenadaalfabeticamente();
}	

@GetMapping("/distanciaentreciudades")
public  String distanciaentreciudades(@RequestParam(name="startLatGrados")String startLatGrados, @RequestParam(name="startLatMinutos") String startLatMinutos, @RequestParam(name="startLatSegundos") String startLatSegundos, @RequestParam(name="startLatOrientacion") String startLatOrientacion,@RequestParam(name="startLonGrados") String startLonGrados, @RequestParam(name="startLonMinutos") String startLonMinutos, @RequestParam(name="startLonSegundos") String startLonSegundos, @RequestParam(name="startLonOrientacion") String startLonOrientacion ,@RequestParam(name="endLatGrados") String endLatGrados, @RequestParam(name="endLatMinutos") String endLatMinutos, @RequestParam(name="endLatSegundos") String endLatSegundos, @RequestParam(name="endLatOrientacion") String endLatOrientacion,@RequestParam(name="endLonGrados") String endLonGrados, @RequestParam(name="endLonMinutos") String endLonMinutos, @RequestParam(name="endLonSegundos") String endLonSegundos, @RequestParam(name="endLonOrientacion") String endLonOrientacion, @RequestParam(name="peaje", required=false ) Boolean peaje){
	
	int istartLatGrados=Integer.valueOf(startLatGrados);
	int istartLatMinutos=Integer.valueOf(startLatMinutos) ; 
	double istartLatSegundos=Double.valueOf(startLatSegundos);
	int istartLonGrados=Integer.valueOf(startLonGrados);  
	int istartLonMinutos=Integer.valueOf(startLonMinutos);
	double istartLonSegundos=Double.valueOf(startLonSegundos);
	int iendLatGrados=Integer.valueOf(endLatGrados);  
	int iendLatMinutos=Integer.valueOf(endLatMinutos);
	double iendLatSegundos=Double.valueOf(endLatSegundos);
	int iendLonGrados=Integer.valueOf(endLonGrados);
	int iendLonMinutos=Integer.valueOf(endLonMinutos);
	double iendLonSegundos=Double.valueOf(endLonSegundos);
	boolean bpeaje=true;

	bpeaje=peaje ;
	
	cityDistancesServices.distanciaEntreciudades(istartLatGrados,istartLatMinutos,istartLatSegundos,startLatOrientacion,istartLonGrados,istartLonMinutos,istartLonSegundos,startLonOrientacion, iendLatGrados,iendLatMinutos,iendLatSegundos,endLatOrientacion,iendLonGrados,iendLonMinutos,iendLonSegundos,endLonOrientacion,bpeaje);
	
	
	
	
	
	
	
	                          //distanciaentreciudades(int startLatGrados,int startLatMinutos,double startLatSstartLatOrientacion,int startLonGrados,int startLonMinutos,double star startLonOrientacion, int endLatGrados,int endLatMinutos, double endLatSegundos, String endLatOrientacion, int endLonGrados,int endLonMinutos, double endLonSegundos, String endLonOrientacion, Boolean peaje)
	return cityDistancesServices.distanciaEntreciudades(istartLatGrados,istartLatMinutos,istartLatSegundos,startLatOrientacion,istartLonGrados,istartLonMinutos,istartLonSegundos,startLonOrientacion, iendLatGrados,iendLatMinutos,iendLatSegundos,endLatOrientacion,iendLonGrados,iendLonMinutos,iendLonSegundos,endLonOrientacion,true);
	
	
}




}