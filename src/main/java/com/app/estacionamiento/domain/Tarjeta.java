package com.app.estacionamiento.domain;

public class Tarjeta {
	private String idTarjeta;
	private String numeroTarjeta;
	private String fechaExpiracion;
	private String codigoSeguridad;

	public Tarjeta() {
		super();
	}
	
	

	public Tarjeta(String idTarjeta, String numeroTarjeta, String fechaExpiracion, String codigoSeguridad) {
		super();
		this.idTarjeta = idTarjeta;
		this.numeroTarjeta = numeroTarjeta;
		this.fechaExpiracion = fechaExpiracion;
		this.codigoSeguridad = codigoSeguridad;
	}



	public String getIdTarjeta() {
		return idTarjeta;
	}



	public void setIdTarjeta(String idTarjeta) {
		this.idTarjeta = idTarjeta;
	}



	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(String fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	@Override
	public String toString() {
		return "Tarjeta [numeroTarjeta=" + numeroTarjeta + ", fechaExpiracion=" + fechaExpiracion + ", codigoSeguridad="
				+ codigoSeguridad + "]";
	}

	
	
	
}
