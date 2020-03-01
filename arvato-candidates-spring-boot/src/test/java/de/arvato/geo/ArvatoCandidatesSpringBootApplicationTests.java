package de.arvato.geo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.arvato.geo.domain.City;
import de.arvato.geo.service.CityDistancesService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArvatoCandidatesSpringBootApplicationTests {

	@Autowired
	CityDistancesService cds;
	
	@Test
	public void contextLoads() {
		
		//prueba numero de ciudades
		float resultado=cds.numerodeciudades();
		assertEquals(14,resultado,0);
		
		////PUNTO 2
		/////////barcelona tarragona con peaje
		//barcelona tarragona
		//http://localhost:8080/citiesanddistances/distanciaentreciudades?startLatGrados=41&startLatMinutos=22&startLatSegundos=58.418&startLatOrientacion=N&startLonGrados=2&startLonMinutos=10&startLonSegundos=38.755&startLonOrientacion=E&endLatGrados=41&endLatMinutos=7&endLatSegundos=2.051&endLatOrientacion=N&endLonGrados=1&endLonMinutos=15&endLonSegundos=16.58&endLonOrientacion=E&peaje=true
		
		String distancia=cds.distanciaEntreciudades(41, 22, 58.418, "N", 2, 10, 38.755, "E", 41, 7, 2.051, "N", 1, 15, 16.58, "E",true);
		String listaordenadaesperada=new String ("DISTANCIA ENTRE DOS CIUDADES "+"<br></br>"+"Distancia calculada por geoUtils"+"<br></br>"+"Distancia calculada entre ciudades despues de convertir de gsm a lat y lon por get Distance"+"<br></br>"+"82.61030641475948"+"<br></br>"+"Busqueda de la ciudad de origen usando lat y lon. LAT=41.38289388888889 LON=2.1774319444444443"+"<br></br>"+"Ciudad origen encontrada: Barcelona"+"<br></br>"+"Busqueda de la ciudad de destino usando lat y lon. LAT=41.11723638888889 LON=1.2546055555555555"+"<br></br>"+"Ciudad destino encontrada: Tarragona"+"<br></br>"+"BUSCANDO RUTA(S) SIMPLES con ORIGEN: Barcelona y DESTINO: Tarragona"+"<br></br>"+"Ruta simple NO encontrada"+"<br></br>"+"BUSCANDO RUTA COMPLEJA: "+"<br></br>"+"Imprimiendo rutas(s) :"+"<br></br>"+"RUTA 1: Tarragona<-Lleida<-Barcelona<-Teruel<-Madrid<-Barcelona .Kilometros totales con peajes: 1539.25"+"<br></br>"+"RUTA 2: Tarragona<-Lleida<-Barcelona .Kilometros totales con peajes: 241.7"+"<br></br>");		
		
		assertEquals(listaordenadaesperada,distancia);
		
		
		///////barcelona madrid con peaje
		//http://localhost:8080/citiesanddistances/distanciaentreciudades?startLatGrados=41&startLatMinutos=22&startLatSegundos=58.418&startLatOrientacion=N&startLonGrados=2&startLonMinutos=10&startLonSegundos=38.755&startLonOrientacion=E&endLatGrados=40&endLatMinutos=25&endLatSegundos=0.137&endLatOrientacion=N&endLonGrados=3&endLonMinutos=42&endLonSegundos=12.897&endLonOrientacion=O&peaje=true
		////barcelona madrid sin el parametro peaje
		//http://localhost:8080/citiesanddistances/distanciaentreciudades?startLatGrados=41&startLatMinutos=22&startLatSegundos=58.418&startLatOrientacion=N&startLonGrados=2&startLonMinutos=10&startLonSegundos=38.755&startLonOrientacion=E&endLatGrados=40&endLatMinutos=25&endLatSegundos=0.137&endLatOrientacion=N&endLonGrados=3&endLonMinutos=42&endLonSegundos=12.897&endLonOrientacion=O
		
		distancia=cds.distanciaEntreciudades(41, 22, 58.418, "N", 2, 10, 38.755, "E", 40, 25, 0.137, "N", 3, 42, 12.897,"O",true);
		String distanciaesperada=new String ("DISTANCIA ENTRE DOS CIUDADES "+"<br></br>"+"Distancia calculada por geoUtils"+"<br></br>"+"Distancia calculada entre ciudades despues de convertir de gsm a lat y lon por get Distance"+"<br></br>"+"505.71508827256247"+"<br></br>"+"Busqueda de la ciudad de origen usando lat y lon. LAT=41.38289388888889 LON=2.1774319444444443"+"<br></br>"+"Ciudad origen encontrada: Barcelona"+"<br></br>"+"Busqueda de la ciudad de destino usando lat y lon. LAT=40.41670472222222 LON=-3.7035825"+"<br></br>"+"Ciudad destino encontrada: Madrid"+"<br></br>"+"BUSCANDO RUTA(S) SIMPLES con ORIGEN: Barcelona y DESTINO: Madrid"+"<br></br>"+"Ruta simple encontrada: "+"<br></br>"+"Distancia con peajes: 564.55"+"<br></br>"+"BUSCANDO RUTA COMPLEJA: "+"<br></br>"+"Imprimiendo rutas(s) :"+"<br></br>"+"RUTA 1: Madrid<-Teruel<-Barcelona .Kilometros totales con peajes: 733.0"+"<br></br>");		
		
		assertEquals(distanciaesperada,distancia);
		http://localhost:8080/citiesanddistances/listarutasconparametros?origen=Barcelona
		
		/////////barcelona madrid sin peaje
		//http://localhost:8080/citiesanddistances/distanciaentreciudades?startLatGrados=41&startLatMinutos=22&startLatSegundos=58.418&startLatOrientacion=N&startLonGrados=2&startLonMinutos=10&startLonSegundos=38.755&startLonOrientacion=E&endLatGrados=40&endLatMinutos=25&endLatSegundos=0.137&endLatOrientacion=N&endLonGrados=2&endLonMinutos=42&endLonSegundos=12.897&endLonOrientacion=O&peaje=false

		distancia=cds.distanciaEntreciudades(41, 22, 58.418, "N", 2, 10, 38.755, "E", 40, 25, 0.137, "N", 3, 42, 12.897,"O",false);
		distanciaesperada=new String ("DISTANCIA ENTRE DOS CIUDADES "+"<br></br>"+"Distancia calculada por geoUtils"+"<br></br>"+"Distancia calculada entre ciudades despues de convertir de gsm a lat y lon por get Distance"+"<br></br>"+"505.71508827256247"+"<br></br>"+"Busqueda de la ciudad de origen usando lat y lon. LAT=41.38289388888889 LON=2.1774319444444443"+"<br></br>"+"Ciudad origen encontrada: Barcelona"+"<br></br>"+"Busqueda de la ciudad de destino usando lat y lon. LAT=40.41670472222222 LON=-3.7035825"+"<br></br>"+"Ciudad destino encontrada: Madrid"+"<br></br>"+"BUSCANDO RUTA(S) SIMPLES con ORIGEN: Barcelona y DESTINO: Madrid"+"<br></br>"+"Ruta simple encontrada: "+"<br></br>"+"Distancia sin peajes: 600.0"+"<br></br>"+"BUSCANDO RUTA COMPLEJA: "+"<br></br>"+"Imprimiendo rutas(s) :"+"<br></br>"+"RUTA 1: Madrid<-Teruel<-Barcelona .Kilometros totales sin peajes: 744.0"+"<br></br>");		
		
		assertEquals(distanciaesperada,distancia);
		
		///////////punto 3
		/////mostrar ciudades ordenadamente
		//http://localhost:8080/citiesanddistances/listaciudadesordenadaalfabeticamente
		
		Iterable<City> is=cds.listaordenadaalfabeticamente();
		String s=new String();
		
		s=is.toString();
		
		
		
		listaordenadaesperada= new String("[{\"myId\":\"402881a6708284c001708284cab20000\",\"name\":\"Barcelona\",\"lat\":41.382893,\"lon\":2.1774323,\"radius\":5,\"longitude\":2.1774323,\"latitude\":41.382893},{\"myId\":\"402881a6708284c001708284cac90005\",\"name\":\"Bilbao\",\"lat\":43.263004,\"lon\":-2.9349916,\"radius\":4,\"longitude\":-2.9349916,\"latitude\":43.263004},{\"myId\":\"402881a6708284c001708284caca0009\",\"name\":\"Cuenca\",\"lat\":40.068115,\"lon\":-2.134824,\"radius\":2,\"longitude\":-2.134824,\"latitude\":40.068115},{\"myId\":\"402881a6708284c001708284cacb000c\",\"name\":\"Cádiz\",\"lat\":36.52983,\"lon\":-6.2925677,\"radius\":3,\"longitude\":-6.2925677,\"latitude\":36.52983},{\"myId\":\"402881a6708284c001708284caca0007\",\"name\":\"Girona\",\"lat\":41.9793,\"lon\":2.819944,\"radius\":3,\"longitude\":2.819944,\"latitude\":41.9793},{\"myId\":\"402881a6708284c001708284cac80002\",\"name\":\"Lleida\",\"lat\":41.61476,\"lon\":0.6267842,\"radius\":2,\"longitude\":0.6267842,\"latitude\":41.61476},{\"myId\":\"402881a6708284c001708284caca000b\",\"name\":\"Logroño\",\"lat\":42.466118,\"lon\":-2.4396677,\"radius\":2,\"longitude\":-2.4396677,\"latitude\":42.466118},{\"myId\":\"402881a6708284c001708284cac80001\",\"name\":\"Madrid\",\"lat\":40.416706,\"lon\":-3.7035825,\"radius\":5,\"longitude\":-3.7035825,\"latitude\":40.416706},{\"myId\":\"402881a6708284c001708284cac90004\",\"name\":\"Málaga\",\"lat\":36.721302,\"lon\":-4.4216366,\"radius\":3,\"longitude\":-4.4216366,\"latitude\":36.721302},{\"myId\":\"402881a6708284c001708284cac90006\",\"name\":\"Salamanca\",\"lat\":40.965157,\"lon\":-5.664018,\"radius\":3,\"longitude\":-5.664018,\"latitude\":40.965157},{\"myId\":\"402881a6708284c001708284caca000a\",\"name\":\"San Sebastian\",\"lat\":43.322422,\"lon\":-1.9838889,\"radius\":3,\"longitude\":-1.9838889,\"latitude\":43.322422},{\"myId\":\"402881a6708284c001708284caca0008\",\"name\":\"Sevilla\",\"lat\":37.38863,\"lon\":-5.9953403,\"radius\":4,\"longitude\":-5.9953403,\"latitude\":37.38863},{\"myId\":\"402881a6708284c001708284cac90003\",\"name\":\"Tarragona\",\"lat\":41.117237,\"lon\":1.2546057,\"radius\":3,\"longitude\":1.2546057,\"latitude\":41.117237},{\"myId\":\"402881a6708284c001708284cacb000d\",\"name\":\"Teruel\",\"lat\":40.343674,\"lon\":-1.1081939,\"radius\":3,\"longitude\":-1.1081939,\"latitude\":40.343674}]");
		listaordenadaesperada=listaordenadaesperada.replace(':','=');
		listaordenadaesperada=listaordenadaesperada.replace("\"","");
		listaordenadaesperada=listaordenadaesperada.replace(" ","");
		listaordenadaesperada=listaordenadaesperada.replace("{","City(");
		listaordenadaesperada=listaordenadaesperada.replace("-","");
		listaordenadaesperada=listaordenadaesperada.replaceAll(",longitude=[[0-9]*.[0-9]*]*","");
		listaordenadaesperada=listaordenadaesperada.replaceAll(",latitude=[[0-9]*.[0-9]*]*","");
		listaordenadaesperada=listaordenadaesperada.replaceAll("myId=[[0-9]*[a-z]*]*,", "myID=,");
		listaordenadaesperada=listaordenadaesperada.replace("}",")");
	
					
		s=s.replace("-","");
		s=s.replaceAll("myId=[[0-9]*[a-z]*]*", "myID=");
		s=s.replace(" ","");

		assertEquals(listaordenadaesperada,s);
		
		
		///////////punto 4
		//buscar rutas origen destino
		//http://localhost:8080/citiesanddistances/listarutasconparametros?origen=Barcelona
		//cds.listarutasconparametros("Barcelona", null);
		//http://localhost:8080/citiesanddistances/listarutasconparametros?destino=Barcelona
		//http://localhost:8080/citiesanddistances/listarutasconparametros?origen=Barcelona&destino=Madrid
		String rutas;
		rutas=cds.listarutasconparametros("Barcelona", "Madrid");
		
		String rutasesperadas= new String ("BUSCANDO RUTA(S) SIMPLES con ORIGEN: Barcelona y DESTINO: Madrid"+"<br></br>"+"Ruta simple encontrada: "+"<br></br>"+"Distancia con peajes: 564.55"+"<br></br>"+"BUSCANDO RUTA COMPLEJA: "+"<br></br>"+"Imprimiendo ruta(s) :"+"<br></br>"+"RUTA 1: Madrid<-Teruel<-Barcelona .Kilometros totales con peajes: 733.0"+"<br></br>");
				
		assertEquals(rutasesperadas,rutas);
		
		//http://localhost:8080/citiesanddistances/listarutasconparametros?origen=Madrid&destino=Barcelona
		//http://localhost:8080/citiesanddistances/listarutasconparametros?origen=Barcelona&destino=Cuenca
		//http://localhost:8080/citiesanddistances/listarutasconparametros?origen=Barcelona&destino=Teruel
		//http://localhost:8080/citiesanddistances/listarutasconparametros?origen=Barcelona&destino=Tarragona
	
		
		///////////punto 5
		//http://localhost:8080/citiesanddistances/listarutasconorigenylimitedekm?origen=Barcelona
		//http://localhost:8080/citiesanddistances/listarutasconorigenylimitedekm?origen=Barcelona&km=1000
		//http://localhost:8080/citiesanddistances/listarutasconorigenylimitedekm?origen=Madrid
		
		
		
		
	}

}
