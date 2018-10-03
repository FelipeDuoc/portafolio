package com.app.estacionamiento.domain;

public class Vehiculo {
	private Integer idVehiculo;
	private String patente;
	private String descripcion;
	
	public Vehiculo(Integer idVehiculo, String patente, String descripcion) {
		super();
		this.idVehiculo = idVehiculo;
		this.patente = patente;
		this.descripcion = descripcion;
	}

	public Vehiculo() {
		super();
	}

	public Integer getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Vehiculo [idVehiculo=" + idVehiculo + ", patente=" + patente + ", descripcion=" + descripcion + "]";
	}
	
	
}
