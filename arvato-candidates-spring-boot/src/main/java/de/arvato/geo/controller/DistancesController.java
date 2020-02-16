package de.arvato.geo.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.arvato.geo.domain.City;
import de.arvato.geo.domain.Distances;
import de.arvato.geo.service.CityService;
import de.arvato.geo.service.DistancesService;

@RestController
@RequestMapping("/distances")

public class DistancesController  {
	
	private DistancesService distancesService;
	
	public DistancesController(DistancesService distancesservice)
	{
		this.distancesService=distancesservice;
	}
	
@GetMapping("/list")
public Iterable<Distances>list(){
return distancesService.list();
}

@GetMapping("/distanciaentreciudades")
//public  String distanciaentreciudades(@RequestParam(name="startLatGrados") int startLatGrados, @RequestParam(name="startLatMinutos") int startLatMinutos, @RequestParam(name="startLatSegundos") double startLatSegundos, @RequestParam(name="startLatOrientacion") String startLatOrientacion,@RequestParam(name="startLonGrados") int startLonGrados, @RequestParam(name="startLonMinutos") int startLonMinutos, @RequestParam(name="startLonSegundos") double startLonSegundos, @RequestParam(name="startLonOrientacion") String startLonOrientacion ,@RequestParam(name="endLatGrados") int endLatGrados, @RequestParam(name="endLatMinutos") int endLatMinutos, @RequestParam(name="endLatSegundos") double endLatSegundos, @RequestParam(name="endLatOrientacion") String endLatOrientacion,@RequestParam(name="endLonGrados") int endLonGrados, @RequestParam(name="endLonMinutos") int endLonMinutos, @RequestParam(name="endLonSegundos") double endLonSegundos, @RequestParam(name="endLonOrientacion") String endLonOrientacion){
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

	
	//if (peaje.isPresent()==false) 
		//	{
	if ((peaje != null)==true)
	{
	bpeaje=peaje ;
	}
	
	//	bpeaje=Boolean.valueOf(peajes);
			//}

	/*
	startLat=41.3828939;
	startLong=2.1774322;
	endLat=40.4167047;
	endLong=-3.7035825;
	*/
	
	
                            //distanciaentreciudades(int startLatGrados,int startLatMinutos,double startLatSegundos,String startLatOrientacion,int startLonGrados,int startLonMinutos,double startLonSegundos,String startLonOrientacion, int endLatGrados,int endLatMinutos, double endLatSegundos, String endLatOrientacion, int endLonGrados,int endLonMinutos, double endLonSegundos, String endLonOrientacion){
	
	
	return distancesService.distanciaentreciudades(istartLatGrados,istartLatMinutos,istartLatSegundos,startLatOrientacion,istartLonGrados,istartLonMinutos,istartLonSegundos,startLonOrientacion, iendLatGrados,iendLatMinutos,iendLatSegundos,endLatOrientacion,iendLonGrados,iendLonMinutos,iendLonSegundos,endLonOrientacion,bpeaje);
	
	
}

}
