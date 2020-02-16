package de.arvato.geo;

//import de.arvato.beans.*;


import de.arvato.geo.controller.CityController;
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
		
		
		//System.out.println(System.getProperty("user.dir"));
		
		
		
	}
	
	
	@Bean
		CommandLineRunner runner1(CityService citiesService) {
			return args -> {
				//lectura del json y escritura a db
				
				ObjectMapper objectMapper =new ObjectMapper();
				TypeReference <List<City>> typeReference = new TypeReference <List<City>>(){};
				InputStream inputStream=TypeReference.class.getResourceAsStream("/data/cities.json");
				Reader reader = new InputStreamReader(inputStream, "UTF-8");
				BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
				
				//String filepath=System.getProperty("user.dir")+"\\"+"src\\main\\resources\\data\\cities.json";	
				//System.out.println("fff"+ filepath);
				
				try {
					//JsonEncoding.UTF16_BE; 
					//JsonGenerator generator;
					//JsonEncoding je=je.UTF32_BE;
					//je.
					//JsonParser jp;
					//JsonFactory factory=new JsonFactory();
					//jp=factory.createJsonParser(inputStream);
					//jp=factory.createParser(inputStream);
					//jp=generator.
					
					 //JsonGenerator jsonGeneratorWithUtf16 = new JsonFactory().createGenerator(new File("C:/outputJson.json"), JsonEncoding.UTF16_BE);
					//.
					 //jsonGeneratorWithUtf16.
					 
					 //new ObjectMapper().writeValue(jsonGeneratorWithUtf16, objectToBeSerialized);
					 //but when I run file -i C:/outputJson.json it shows charset=binary.		
							
							
							
							
					//jp=factory.createGenerator(new File ("../data/cities.json"), JsonEncoding.UTF32_BE);			
					//jp.
					//JsonGenerator jg;
					//File f= new File(inputStream);
					
					//List<City> cities=objectMapper.readValue(new File (filepath), typeReference);
					//List<City> cities=objectMapper.readValue(jp, typeReference);
					
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
}

/*
@Bean
CommandLineRunner runner3(GeoUtils geo) {
	return args -> {
		
};
}


}
*/
	//@Bean
	/*CommandLineRunner runner(CitiesService citiesService) {
		return args -> {
			*/
	
	/*@Override
    public void run(String[] args) throws IOException {
		
			ObjectMapper objectMapper =new ObjectMapper();
			
			//JsonNode rootNode = objectMapper.readTree(new File("customer.json"));
			
			// City city = objectMapper.readValue(new File("cities.json"), City.class);
			
			 City city = objectMapper.readValue(new File(getClass().getClassLoader().getResource("data/cities.json").getFile()), City.class);
				
			 System.out.println(city);
			
			//TypeReference<List<City>> typeReference = new TypeReference <List<City>>() {};
			
		}
			// read json and write to db
		//};
	 */
/*	@Bean
	CommandLineRunner runner(CitiesService cityService) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
			try {
				List<User> users = mapper.readValue(inputStream,typeReference);
				userService.save(users);
				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}*/

//}
