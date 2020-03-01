package de.arvato.geo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.arvato.geo.domain.Boolfloat;
import de.arvato.geo.domain.Boolint;
import de.arvato.geo.domain.City;
import de.arvato.geo.domain.CityDistances;
import de.arvato.geo.domain.Distances;
import de.arvato.geo.domain.Stringfloat;
import de.arvato.geo.repository.CityDistancesRepository;
import de.arvato.geo.repository.CityRepository;
import de.arvato.geo.repository.DistancesRepository;
import de.arvato.geo.utils.GeoUtils;
import de.arvato.geo.utils.GeoUtils.O;
@Service
public class CityDistancesService {


	private static final Logger LOGGER= Logger.getLogger(Service.class);

	private List<Stringfloat> lsi;
	private float distancia_total;
	private String ruta;
	private int contador_recursivo;
	private int ibc;
	
	@Autowired
	List<Distances> distancesno;

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
	//	if (peaje==false)
		//{
			//s=s+"Ruta sin peajes"+"<br></br>";
		//}
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

		s=s+"Busqueda de la ciudad de destino usando lat y lon. LAT="+endlatorigin+" LON="+endlonorigin+"<br></br>";
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
			List<Distances> distances=(List<Distances>)distanceService.listaordenadaalfabeticamente();
			s=s+"BUSCANDO RUTA(S) SIMPLES con ORIGEN: "+cities.get(biorigen.getI()).getName()+" y DESTINO: "+cities.get(bidestino.getI()).getName()+"<br></br>";
			
			//busqueda de una ruta simple de las dos ciudades en la lista de distancias
			bibs=this.busquedasimplederutas(cities.get(biorigen.getI()).getName(), cities.get(bidestino.getI()).getName(), distances) ;
			//Ruta simple encontrada
			if (bibs.isB())
			{
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
				s=s+"Ruta simple NO encontrada"+"<br></br>";
				//String ruta;
			}
				//distancesno=(List<Distances>)distanceService.list();
				s=funcion_busqueda_compleja(s,cities.get(biorigen.getI()).getName(), cities.get(bidestino.getI()).getName(), peaje);
			/*
				contador_recursivo=0;
				s=s+"Buscando ruta compleja: "+"<br></br>";
				distancia_total=0;
				ruta=new String();
				boolean busquedacomplejaencontrada=false;
				//ruta=ruta+cities.get(biorigen.getI()).getName();
				System.out.println("Rutaaaaaa origen "+ruta);
				 distancesno=(List<Distances>)distanceService.list();
				lsi=new ArrayList<Stringfloat>();	
				Stringfloat sf= new Stringfloat();
				lsi.add(sf);
				ibc=0;
				int numero_rutas=0;
				try {
					busquedacomplejaencontrada=this.busquedacomplejarutas(numero_rutas,cities.get(biorigen.getI()).getName(), cities.get(bidestino.getI()).getName(), peaje,0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				//ruta=ruta+
			
				
				
				//Collections.reverse(lsi);
				//if (busquedacomplejaencontrada)
				if (lsi.size()>1)
				{
				
					s=s+"Imprimiendo rutas(s) :"+"<br></br>";
					lsi.remove(lsi.size()-1);
					System.out.println("busqueda compleja encontrada");
					//Collections.reverse(lsi.get);
					//lsi.add(0,)
					for (int i=0; i<lsi.size(); i++)
					{
						
						if (peaje)
						{
						//s=s+"RUTA "+(i+1)+": "+lsi.get(i).getS()+cities.get(biorigen.getI()).getName()+"  .Kilometros totales con peajes: "+lsi.get(i).getI()+"<br></br>";
						s=s+"RUTA "+(i+1)+": "+lsi.get(i).getS()+"<-"+cities.get(biorigen.getI()).getName()+" .Kilometros totales con peajes: "+lsi.get(i).getI()+"<br></br>";
						
						}
						else
						{
						s=s+"RUTA "+(i+1)+": "+lsi.get(i).getS()+"<-"+cities.get(biorigen.getI()).getName()+" .Kilometros totales sin peajes: "+lsi.get(i).getI()+"<br></br>";
								
						}
					}


				}




			

		}





		return s;
	




	}
	public String busqueda_simple_y_compleja(String origen, String destino, Boolean peaje, Boolfloat haylimitekm)
	{
		String s=new String();
		Boolint bibs=new Boolint();
		List<Distances> distances=(List<Distances>)distanceService.listaordenadaalfabeticamente();
		
		s=s+"BUSCANDO RUTA(S) SIMPLES con ORIGEN: "+origen+" y DESTINO: "+destino+"<br></br>";
		
		//busqueda de una ruta simple de las dos ciudades en la lista de distancias
		bibs=this.busquedasimplederutas(origen, destino, distances) ;
		//Ruta simple encontrada
				
		if (bibs.isB())
		{
			s=s+"Ruta simple encontrada: "+"<br></br>";
			if (peaje)
			{
				if ((haylimitekm.isB()==false)||((haylimitekm.isB()==true)&&(distances.get(bibs.getI()).getDistance().getToll()< haylimitekm.getI())))
				{
				s=s+"Distancia con peajes: "+distances.get(bibs.getI()).getDistance().getToll()+"<br></br>";
				}
				else
				{
					s=s+"No se ha encontrado ninguna ruta simple que este por debajo el limite de los "+haylimitekm.getI()+" km "+"<br></br>";
				}
				
				
			}
			else
			{
				if ((haylimitekm.isB()==false)||((haylimitekm.isB()==true)&&(distances.get(bibs.getI()).getDistance().getfree()< haylimitekm.getI())))
				{
					s=s+"Distancia sin peajes: "+distances.get(bibs.getI()).getDistance().getfree()+"<br></br>";
				}
				else
				{
					s=s+"No se ha encontrado ninguna ruta simple que este por debajo el limite de los "+haylimitekm.getI()+" km "+"<br></br>";
				}
			
			}
		}else
		{
			s=s+"Ruta simple NO encontrada"+"<br></br>";
			//String ruta;
		}
			//distancesno=(List<Distances>)distanceService.list();
			s=funcion_busqueda_compleja(s,origen, destino, peaje);
			/*
			contador_recursivo=0;
			s=s+"Buscando ruta compleja: "+"<br></br>";
			distancia_total=0;
			ruta=new String();
			boolean busquedacomplejaencontrada=false;
			//ruta=ruta+cities.get(biorigen.getI()).getName();
			System.out.println("Rutaaaaaa origen "+ruta);
			 distancesno=(List<Distances>)distanceService.list();
			lsi=new ArrayList<Stringfloat>();	
			Stringfloat sf= new Stringfloat();
			lsi.add(sf);
			ibc=0;
			int numero_rutas=0;
			try {
				busquedacomplejaencontrada=this.busquedacomplejarutas(numero_rutas,origen, destino, peaje,0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			//ruta=ruta+
		
			//Collections.reverse(lsi);
			if (lsi.size()>1)
			{
				s=s+"Imprimiendo ruta(s) :"+"<br></br>";
				lsi.remove(lsi.size()-1);
				System.out.println("busqueda compleja encontrada");
				//Collections.reverse(lsi.get);
				//lsi.add(0,)
			
				for (int i=0; i<lsi.size(); i++)
				{
					if (peaje)
					{
						if ((haylimitekm.isB()==false)||((haylimitekm.isB()==true)&&(lsi.get(i).getI()< haylimitekm.getI())))
						{
					//s=s+"RUTA "+(i+1)+": "+lsi.get(i).getS()+cities.get(biorigen.getI()).getName()+"  .Kilometros totales con peajes: "+lsi.get(i).getI()+"<br></br>";
					s=s+"RUTA "+(i+1)+": "+lsi.get(i).getS()+"<-"+origen+" .Kilometros totales con peajes: "+lsi.get(i).getI()+"<br></br>";
					
						}
						else
						{
							s=s+"RUTA "+(i+1)+": "+"Esta ruta compleja no esta por debajo el limite de los "+haylimitekm.getI()+" km "+"<br></br>";
						}
						
					}
					else
					{	if ((haylimitekm.isB()==false)||((haylimitekm.isB()==true)&&(lsi.get(i).getI()< haylimitekm.getI())))
						{
						s=s+"RUTA "+(i+1)+": "+lsi.get(i).getS()+"<-"+origen+" .Kilometros totales sin peajes: "+lsi.get(i).getI()+"<br></br>";
						}
						else
						{
							s=s+"RUTA "+(i+1)+": "+"Esta ruta compleja no esta por debajo el limite de los "+haylimitekm.getI()+" km "+"<br></br>";
						}
					}
				}
					
			
			}else
			{
				s=s+"No se han encontrado rutas complejas"+"<br></br>";
			}
			return s;
	}
	
	public String funcion_busqueda_compleja(String s,String origen, String destino, boolean peaje)
	{
		contador_recursivo=0;
		s=s+"BUSCANDO RUTA COMPLEJA: "+"<br></br>";
		distancia_total=0;
		ruta=new String();
		boolean busquedacomplejaencontrada=false;
		//ruta=ruta+cities.get(biorigen.getI()).getName();
		System.out.println("Rutaaaaaa origen "+ruta);
		 distancesno=(List<Distances>)distanceService.list();
		lsi=new ArrayList<Stringfloat>();	
		Stringfloat sf= new Stringfloat();
		lsi.add(sf);
		ibc=0;
		int numero_rutas=0;
		try {
			busquedacomplejaencontrada=this.busquedacomplejarutas(numero_rutas,origen, destino, peaje,0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ruta=ruta+
	
		return s;
		
	}
	
	public String  listarutasconparametros( String origen, String destino)
	{
		List<Distances> distances=(List<Distances>)distanceService.list();
		String s=new String();
		System.out.println("oorigen"+origen);
		System.out.println("ddestino"+destino);
		
		if ((origen==null)&&(destino==null))
		{
			s=s+"Sin parametros de entrada"+"<br></br>";
			
			//s=distanceService.list().toString();
			for (int i=0; i<distances.size();i++)
			{
				s=s+"Ruta "+(i)+":<br></br>";
				s=s+"Ciudad Origen: "+distances.get(i).getOrigin()+"<br></br>";
				s=s+"Ciudad Destino: "+distances.get(i).getdestination()+"<br></br>";
			}
			
			
		}
		else if ((origen==null)&&(destino!=null))
		{   
				boolean b=false;			
				s=s+"Ruta(s) con el destino especificado"+"<br></br>";
				
				for (int i=0; i<distances.size();i++)
				{
					
					if (distances.get(i).getdestination().equals(destino))
					{	b=true;
						s=s+"Ruta "+(i)+":<br></br>";
						s=s+"Ciudad Destino: "+distances.get(i).getdestination()+"<br></br>";
						s=s+"Ciudad Origen: "+distances.get(i).getOrigin()+"<br></br>";
					}
				}
				if (!b)
					{
					s=s+"No se han encontrado rutas con el destino especificado"+"<br></br>";
					}
				
			
		}else if ((origen!=null)&&(destino==null))
		{
			/*s=s+"Ruta(s) simples con el origen especificado"+"<br></br>";
			boolean b=false;
			//hace una busqueda muy simple, tendria que hacer una compleja
			for (int i=0; i<distances.size();i++)
			{
				if (distances.get(i).getOrigin().equals(origen))
				{	b=true;
					s=s+"Ruta "+(i)+":<br></br>";
					s=s+"Ciudad Origen: "+distances.get(i).getOrigin()+"<br></br>";
					s=s+"Ciudad Destino: "+distances.get(i).getdestination()+"<br></br>";
				}
				
				
					
			}
			
			if (!b)
			{
			s=s+"No se han encontrado rutas simples con el destino especificado"+"<br></br>";
			}
			*/
			/*
			List<City> cities=(List<City>)cityService.listaordenadaalfabeticamente();
			//seria algo asi:
			s=s+"Buscando todas las rutas con origen :"+origen+"<br></br>";
			Boolfloat nohaylimitekm=new Boolfloat();
			for (int i=0; i< cities.size(); i++)
			{
			s=s+this.busqueda_simple_y_compleja(origen, cities.get(i).getName(), true,nohaylimitekm);
			}
			*/
			s=s+listarutasconorigenylimitedekm (origen,-100);
			
		}else if ((origen!=null)&&(destino!=null))
		{
		
			Boolfloat nohaylimitekm=new Boolfloat();
			 s=s+this.busqueda_simple_y_compleja(origen, destino, true,nohaylimitekm);
			
		}
		
		return s;
	}

	
	public String listarutasconorigenylimitedekm (String origen, float km)
	{
		Boolfloat haylimitekm=new Boolfloat();
		if  (km==-100)
		{
			haylimitekm.setB(false);
		
		}else
		{
			haylimitekm.setB(true);
			haylimitekm.setI(km);
			
		}
		
		String s=new String();
		List<City> cities=(List<City>)cityService.listaordenadaalfabeticamente();
		s=s+"BUSCANDO TODAS LAS RUTAS CON ORIGEN : "+origen+"<br></br>";
		for (int i=0; i< +cities.size() ; i++)
		{	
			s=s+this.busqueda_simple_y_compleja(origen, cities.get(i).getName(), true,haylimitekm);
			s=s+"-----------------------------------------------------------------------------------"+"<br></br>";
		}
		
		return s;
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
	private Boolint busquedasimplederutas(String ciudadorigen, String ciudaddestino, List<Distances> distances)
	{   //BUSCA UNA RUTA SIMPLE, ES INDIFERENTE EL ORDEN ENTRE ORIGEN Y DESTINO, SI EXISTE UN CAMINO DA POR SUPUESTO QUE ES EN DOBLE SENTIDO (IDA Y VUELTA)
		//no hace falta trabajar con la lista distancias ordenada, sin embargo la ordene porque en un futuro se puede ganar eficiencia en el algoritmo trabajando con una lista ordenada pues no haria falta recorrer toda la lista si esta esta ordenada.
		Boolint bi=new Boolint();
		//List<Distances> distances=(List<Distances>)distanceService.list();
		//List<Distances> distances=(List<Distances>)distanceService.listaordenadaalfabeticamente();

		int i=0;
		while ((i<distances.size() )&&(bi.isB()==false))
		{
			if (ciudadorigen.equals(distances.get(i).getOrigin()))
			{
				if (ciudaddestino.equals(distances.get(i).getdestination()))
				{
					bi.setB(true);
					bi.setI(i);
				}
			}else if (ciudadorigen.equals(distances.get(i).getdestination()))
			{

				if (ciudaddestino.equals(distances.get(i).getOrigin()))
				{
					bi.setB(true);
					bi.setI(i);
				}
				
			}

			i++;
		}

		return bi;
	}
	
	//private boolean busquedacomplejarutas(List<Stringfloat> lsi , int numero_ruta_encontrada,String ciudadorigen, String ciudaddestino,List<Distances> distances,boolean peaje, float distancia_total, String ruta)
	//private boolean busquedacomplejarutas( int numero_ruta_encontrada,String ciudadorigen, String ciudaddestino,List<Distances> distances,boolean peaje, float distancia_total, String ruta)
	private boolean busquedacomplejarutas( int numero_ruta_encontrada,String ciudadorigen, String ciudaddestino,boolean peaje,int ibc) throws InterruptedException
	{
		//no hace falta trabajar con la lista distancias ordenada, sin embargo la ordene porque en un futuro se puede ganar eficiencia en el algoritmo trabajando con una lista ordenada pues no haria falta recorrer toda la lista si esta esta ordenada.
		boolean b=false;
		//int i=0;
		//if (ciudadorigen!=ciudaddestino)
		//{
		 //Thread.sleep(50);
		 // distancesno=(List<Distances>)distanceService.list();
		  System.out.println(distancesno.size());
			System.out.println("Llamada a la funcion");
			while (ibc<distancesno.size())
			{
				System.out.println(ibc);
				boolean casnormal=true;
				if ((ciudadorigen.equals(distancesno.get(ibc).getOrigin()))||(ciudadorigen.equals(distancesno.get(ibc).getdestination())))
				{	
					// Thread.sleep(50);
					System.out.println("Ciudad origen macht"+ciudadorigen);
					String ciudadoricenorigen;
					if(ciudadorigen.equals(distancesno.get(ibc).getOrigin()))
					{
					ciudadoricenorigen=distancesno.get(ibc).getdestination();
					//casnormal=true;
					}
					else
					{
					casnormal=false;
					ciudadoricenorigen=distancesno.get(ibc).getOrigin();	
					}
						
					Distances d=distancesno.get(ibc);
					distancesno.remove(ibc);
					//List<Distances> distancesno=(List<Distances>)distanceService.list();

					//List<Distances> distancees=(List<Distances>)distanceService.listaordenadaalfabeticamente();
					System.out.println("aI"+ibc);	
					
					
					contador_recursivo=contador_recursivo+1;
					//ibc=0;
					
					b=busquedacomplejarutas(numero_ruta_encontrada,ciudadoricenorigen , ciudaddestino,peaje,0);
					distancesno.add(ibc,d);
					contador_recursivo=contador_recursivo-1;
					System.out.println("Busqueda compleja resultado"+b);	
					// contador recursivo se usa para que no encuetre rutas simples.
					if ((ciudadoricenorigen.equals(ciudaddestino))&&(contador_recursivo>0))
						
					{	
						ruta=new String();
						distancia_total=0;
						
						// Thread.sleep(50);
						System.out.println("COntador recursivo"+contador_recursivo);
						System.out.println("destino=destino busqueda compleja encontrada"+ciudadorigen);
						if (peaje)
						{
							distancia_total=distancia_total+distancesno.get(ibc).getDistance().getToll();
						}
						else
						{
							distancia_total=distancia_total+distancesno.get(ibc).getDistance().getfree();
						}
						if (casnormal)
						{
						ruta=ruta+distancesno.get(ibc).getdestination();
						}
						else
						{
						ruta=ruta+distancesno.get(ibc).getOrigin();
						}
						System.out.println("bce ruta"+ruta);
						System.out.println("bce distancia total"+distancia_total);
						/*
						Stringfloat sf= new Stringfloat();
						lsi.add(sf);
						lsi.get(numero_ruta_encontrada).setS(ruta);
						lsi.get(numero_ruta_encontrada).setI(distancia_total);
						lsi.add(sf);
						*/
						b=true;
						return b;
					}
					
					if ((b) && (ciudadoricenorigen.equals(ciudaddestino)==false))
					{
						System.out.println("busqueda compleja encontrada");	
						if (peaje)
						{
							distancia_total=distancia_total+distancesno.get(ibc).getDistance().getToll();
						}
						else
						{
							distancia_total=distancia_total+distancesno.get(ibc).getDistance().getfree();
						}
					
						System.out.println("RUTA"+ ruta);	
						if (casnormal)
						{
						ruta=ruta+"<-"+distancesno.get(ibc).getdestination();
						}
						else
						{
						ruta=ruta+"<-"+distancesno.get(ibc).getOrigin();
						}
						
						System.out.println("I"+ibc);
						System.out.println("contador_recursivo"+contador_recursivo);
						
						if (contador_recursivo==0)
						{
							Stringfloat sf= new Stringfloat();
							lsi.add(sf);
							
							System.out.println("i=0");	
							System.out.println(ruta);
							System.out.println(distancia_total);
							lsi.get(numero_ruta_encontrada).setS(ruta);
							lsi.get(numero_ruta_encontrada).setI(distancia_total);
							//lsi.add(sf);
							ruta=new String();
							distancia_total=0;
							numero_ruta_encontrada++;
						}
						
					}
								
					/*	if (i==0)
						{
							//Stringfloat sf= new Stringfloat();
							//lsi.add(sf);
							System.out.println("i=0");	
							System.out.println(ruta);
							System.out.println(distancia_total);
							lsi.get(numero_ruta_encontrada).setS(ruta);
							lsi.get(numero_ruta_encontrada).setI(distancia_total);
							numero_ruta_encontrada++;
						}*/
						
						//return b;
					//}	
				}

				ibc++;
			}	
				
		return b;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
