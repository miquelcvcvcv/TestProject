package de.arvato.geo;

//import de.arvato.beans.*;



import de.arvato.geo.service.CityDistancesService;
import de.arvato.geo.domain.City;
import de.arvato.geo.domain.Distances;


import org.springframework.context.annotation.Bean;





import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

import java.util.List;


@SpringBootApplication
public class ArvatoCandidatesSpringBootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ArvatoCandidatesSpringBootApplication.class, args);
		
		
		System.out.println("Ejecutando");
		
		
		
	}
	@Bean
	CommandLineRunner runnerCityDistances (CityDistancesService citiesDistancesService) {
		return args -> {
			
			ObjectMapper objectMapper =new ObjectMapper();
			TypeReference <List<City>> typeReference = new TypeReference <List<City>>(){};
			InputStream inputStream=TypeReference.class.getResourceAsStream("/data/cities.json");
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
			
			try {
				
				List<City> cities=objectMapper.readValue(in, typeReference);
				
				citiesDistancesService.saveAllcities(cities);
				
				System.out.println("Cities Saved");
			} catch (IOException e)
			{
				System.out.println("Unable to save cities"+e.getMessage());
			}
			
			ObjectMapper objectMapperd =new ObjectMapper();
			TypeReference <List<Distances>> typeReferenced = new TypeReference <List<Distances>>(){};
			InputStream inputStreamd=TypeReference.class.getResourceAsStream("/data/distances.json");
			BufferedReader ind = new BufferedReader(new InputStreamReader(inputStreamd, StandardCharsets.ISO_8859_1));
							
			try {

				List<Distances> distances=objectMapperd.readValue(ind, typeReferenced);
				
				citiesDistancesService.saveAllDistances(distances);     
				
				System.out.println("Distances Saved");
				
			} catch (IOException e)
			{
				System.out.println("Unable to save distances"+e.getMessage());
			}
	};
	}
	
}


