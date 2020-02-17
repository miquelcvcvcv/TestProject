package de.arvato.geo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.arvato.geo.domain.City;
import de.arvato.geo.service.CityService;

@RestController
@RequestMapping("/cities")

public class CityController  {
	
	private CityService cityService;
	
	public CityController(CityService cityservice)
	{
		this.cityService=cityservice;
	}
		
@GetMapping("/listcities")
public Iterable<City>list(){
return cityService.list();
}
@GetMapping("/listaordenadaalfabeticamente")
public  Iterable<City> listaordenadaalfabeticamente(){
	return cityService.listaordenadaalfabeticamente();
}

@GetMapping("/numerodeciudades")
public long numerodeciudades(){
	return cityService.numerodeciudades();
}

	
}
