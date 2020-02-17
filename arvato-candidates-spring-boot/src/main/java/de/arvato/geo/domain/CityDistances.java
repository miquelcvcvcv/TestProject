package de.arvato.geo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
@Entity




public class CityDistances {
	private static final long serialVersionUID = 5755322007256239505L;
	
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	
	private City cities;

	private Distances distances;
	
	public City getCities() {
		return cities;
	}
	public void setCities(City cities) {
		this.cities = cities;
	}
	public Distances getDistances() {
		return distances;
	}
	public void setDistances(Distances distances) {
		this.distances = distances;
	}
	

	public CityDistances() {}
	
}
