package de.arvato.geo.domain;

public class Ruta {
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
      
		
		return this.distancia.compareToIgnoreCase(ruta1.distancia);
    }

	

}
