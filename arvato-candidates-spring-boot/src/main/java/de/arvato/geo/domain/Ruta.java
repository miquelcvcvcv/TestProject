package de.arvato.geo.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
@Entity
@Component

public class Ruta  implements  Comparable<Ruta>, Serializable { 

	private static final long serialVersionUID = 7758717594358781710L;

	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")

	
	
	
	private String origen;
	private String destino;
	private float distancia;
	private String ruta;
	
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public float getDistancia() {
		return distancia;
	}
	public void setDistancia(float distancia) {
		this.distancia = distancia;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public Ruta() {
		origen=new String();
		destino=new String();
		distancia=0;
		ruta=new String();
		
	}
	
	@Override
    public int compareTo(Ruta ruta1) {
		
  
		return  (int) (this.distancia- ruta1.distancia );
	}
	
	
	

}
