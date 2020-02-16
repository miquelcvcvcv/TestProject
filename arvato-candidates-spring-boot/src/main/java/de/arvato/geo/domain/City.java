package de.arvato.geo.domain;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@Entity

public class City implements Comparable<City>, Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5755322007256239505L;
	//@Id	@GeneratedValue (strategy=GenerationType.AUTO)
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String myId;
	/*"name" : "Lleida",
	"lat" : 41.6147605,
	"lon" : 0.6267842,
	"radius" : 2
	*/
	private String name;
	private float lat;
	private float lon;
	private short radius;
	
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}

	public float getLatitude()
	{
		return this.lat;
	}
	
	public void setLatitude(float latitude)
	{
		this.lat=latitude;		
	}
	
	public float getLongitude()
	{
		return this.lon;
	}
	
	public void setLongitude(float longitude)
	{
		this.lon=longitude;
	}
	
	public short getRadius()
	{
		return this.radius;
	}
	
	public void setRadius(short radius)
	{
	 this.radius=radius;
	}
	/*
	@Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", lat='" + lat + '\'' +
                ", lon=" + lon +
                ", radius='" + radius + '\'' +
                '}';
    }
	*/
  
	@Override
    public int compareTo(City city1) {
        return this.name.compareToIgnoreCase(city1.name);
    }

	
    public boolean findCity(float latitude, float longitud) {
        //return this.name.compareToIgnoreCase(city1.name);
		boolean b=false;
    	
		 if ((this.lat==latitude) && (this.lon==longitud))
		 {
			 b=true;
		 }
		
		return b;	
	}
	
	public City() {}
}
