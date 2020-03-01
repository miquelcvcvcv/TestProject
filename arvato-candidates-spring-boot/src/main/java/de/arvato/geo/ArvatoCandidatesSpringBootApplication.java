package de.arvato.geo;

//import de.arvato.beans.*;


import de.arvato.geo.controller.CityController;
import de.arvato.geo.service.CityDistancesService;
import de.arvato.geo.service.CityService;
import de.arvato.geo.service.DistancesService;
import de.arvato.geo.utils.GeoUtils;
import de.arvato.geo.domain.City;
import de.arvato.geo.domain.Distances;

//import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			//BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
			
			try {
				
				//List<City> cities=objectMapper.readValue(inputStream, typeReference);
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
			Reader readerd = new InputStreamReader(inputStreamd, "UTF-8");
			//BufferedReader ind = new BufferedReader(new InputStreamReader(inputStreamd, StandardCharsets.UTF_8));
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
	/*
	@Bean
		CommandLineRunner runner1(CityService citiesService) {
			return args -> {
				//lectura del json y escritura a db
				
				ObjectMapper objectMapper =new ObjectMapper();
				TypeReference <List<City>> typeReference = new TypeReference <List<City>>(){};
				InputStream inputStream=TypeReference.class.getResourceAsStream("/data/cities.json");
				Reader reader = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				
				
				try {
			
					//List<City> cities=objectMapper.readValue(inputStream, typeReference);
					List<City> cities=objectMapper.readValue(in, typeReference);
					
					citiesService.saveAll(cities);
					
					System.out.println("Cities Saved");
				} catch (IOException e)
				{
					System.out.println("Unable to save cities"+e.getMessage());
				}
				
				//City city = objectMapper.readValue(new File(getClass().getClassLoader().getResource("data/cities.json").getFile()), City.class);
				 //System.out.println(city);
			};
	}

	
@Bean
CommandLineRunner runner2(DistancesService distancesService) {
	return args -> {
		//lectura del json y escritura a db
		
		ObjectMapper objectMapper =new ObjectMapper();
		TypeReference <List<Distances>> typeReference = new TypeReference <List<Distances>>(){};
		InputStream inputStream=TypeReference.class.getResourceAsStream("/data/distances.json");
		Reader reader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
						
		try {

			List<Distances> distances=objectMapper.readValue(in, typeReference);
			
			distancesService.saveAll(distances);
			
			System.out.println("Distances Saved");
		} catch (IOException e)
		{
			System.out.println("Unable to save distances"+e.getMessage());
		}
		
		
	};

}
*/
}


