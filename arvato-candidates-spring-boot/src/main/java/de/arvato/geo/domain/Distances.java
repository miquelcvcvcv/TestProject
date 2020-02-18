package de.arvato.geo.domain;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity

public class Distances implements  Comparable<Distances>, Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7758717596358781710L;

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String myId;
	/*
	"origin": 		"Bilbao",
	"destination": 	"Salamanca",
	"distance" : {
		"toll" : 397.87,
		"free" : 419.978
	}
	*/
	
	private String origin;
	private String destination;
	
	
	@Embedded
	private Distance distance;
	
	public String getOrigin()
	{
		return this.origin;
	}
	public void setOrigin(String origin)
	{
		this.origin=origin;
	}
	public String  getdestination()
	{
		return this.destination;
	}
	public void setDestination(String destination)
	{
		this.destination=destination;
	}
	
	public Distance getDistance()
	{
		return this.distance;
	}
	public void setDistance (Distance dis)
	{
		this.distance=dis;
	}
	
	
	@Override
    public int compareTo(Distances dis1) {
        return this.origin.compareToIgnoreCase(dis1.origin);
    }


	
	/*
	@Override
    public String toString() {
        return "{" +
                "origin='" + origin + '\'' +
                ", city='" + destination + '\'' +
                                '}';
    }
	*/
	public Distances () {}
}