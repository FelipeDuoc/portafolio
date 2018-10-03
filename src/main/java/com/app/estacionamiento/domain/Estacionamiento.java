package com.app.estacionamiento.domain;


public class Estacionamiento {
	private Integer idEstacionamiento;
	private String descripcion;
	private String tarifa;
	
	public Estacionamiento(Integer idEstacionamiento, String descripcion, String tarifa) {
		super();
		this.idEstacionamiento = idEstacionamiento;
		this.descripcion = descripcion;
		this.tarifa = tarifa;
	}

	public Estacionamiento() {
		super();
	}

	public Integer getIdEstacionamiento() {
		return idEstacionamiento;
	}

	public void setIdEstacionamiento(Integer idEstacionamiento) {
		this.idEstacionamiento = idEstacionamiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTarifa() {
		return tarifa;
	}

	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

	@Override
	public String toString() {
		return "Estacionamiento [idEstacionamiento=" + idEstacionamiento + ", descripcion=" + descripcion + ", tarifa="
				+ tarifa + "]";
	}
	
	
}
