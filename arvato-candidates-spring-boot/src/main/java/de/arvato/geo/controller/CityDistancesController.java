package de.arvato.geo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.arvato.geo.domain.City;
import de.arvato.geo.domain.Distances;
import de.arvato.geo.service.CityDistancesService;

@RestController
@RequestMapping("/citiesanddistances")

public class CityDistancesController {




	@Autowired
	private CityDistancesService cityDistancesServices;
	

	/*public CityDistancesController( CityDistancesService  cityDistancesServices)
	{
		this.cityDistancesServices= cityDistancesServices;
	}
	*/
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
	public  String distanciaentreciudades(@RequestParam(name="startLatGrados")String startLatGrados, @RequestParam(name="startLatMinutos") String startLatMinutos, @RequestParam(name="startLatSegundos") String startLatSegundos, @RequestParam(name="startLatOrientacion") String startLatOrientacion,@RequestParam(name="startLonGrados") String startLonGrados, @RequestParam(name="startLonMinutos") String startLonMinutos, @RequestParam(name="startLonSegundos") String startLonSegundos, @RequestParam(name="startLonOrientacion") String startLonOrientacion ,@RequestParam(name="endLatGrados") String endLatGrados, @RequestParam(name="endLatMinutos") String endLatMinutos, @RequestParam(name="endLatSegundos") String endLatSegundos, @RequestParam(name="endLatOrientacion") String endLatOrientacion,@RequestParam(name="endLonGrados") String endLonGrados, @RequestParam(name="endLonMinutos") String endLonMinutos, @RequestParam(name="endLonSegundos") String endLonSegundos, @RequestParam(name="endLonOrientacion") String endLonOrientacion, @RequestParam(required=false ) Boolean peaje){

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

		if (peaje==null)

		{
			peaje=true;
		}
		bpeaje=peaje ;

		return cityDistancesServices.distanciaEntreciudades(istartLatGrados,istartLatMinutos,istartLatSegundos,startLatOrientacion,istartLonGrados,istartLonMinutos,istartLonSegundos,startLonOrientacion, iendLatGrados,iendLatMinutos,iendLatSegundos,endLatOrientacion,iendLonGrados,iendLonMinutos,iendLonSegundos,endLonOrientacion,bpeaje);
	}

	@GetMapping("/listarutasconparametros")
	public  String listarutasconparametros(@RequestParam(required=false)String origen, @RequestParam(required=false ) String destino){

		System.out.println("origen"+origen);
		System.out.println("destino"+destino);

		return cityDistancesServices.listarutasconparametros(origen, destino);
	}
	@GetMapping("/listarutasconorigenylimitedekm")
	public  String listarutasconorigenylimitedekm(@RequestParam(name="origen")String origen, @RequestParam(required=false ) String km){


		System.out.println("origen"+origen);
		float fkm=-100;


		if (km!=null)
		{
			fkm=Float.valueOf(km);
		}


		return cityDistancesServices.listarutasconorigenylimitedekm( origen, fkm);
	}
	@GetMapping("/listaordenadarutasconorigenylimitedekm")
	public  String listaordenadarutasconorigenylimitedekm(@RequestParam(name="origen")String origen, @RequestParam(required=false ) String km){

		System.out.println("origen"+origen);
		float fkm=-100;


		if (km!=null)
		{
			fkm=Float.valueOf(km);
		}


		return cityDistancesServices.listaordenadarutasconorigenylimitedekm( origen, fkm);
	}			



}