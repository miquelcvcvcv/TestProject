package de.arvato.geo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
/*
"origin": 		"Barcelona",
"destination": 	"Lleida",
"distance" : {
	"toll" : 140.7,
	"free" : 160
*/	
public class Distance implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6608515872347292358L;
	//@Column (name="distance")
	
	private float toll;
	private short free;

	
	public float getToll ()
	{
		return this.toll;
	}
	public void setToll (float toll)
	{
		this.toll=toll;
	}
	public short getfree ()
	{
		return this.free;
	}
	public void setfree (short free)
	{
		this.free=free;
	}


	public Distance() {}
}
