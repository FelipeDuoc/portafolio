package com.app.estacionamiento.domain;

public class Calificacion {
	private String promedio_cliente;
	private String promedio_dueno;
	
	public Calificacion() {
		super();
	}

	public Calificacion(String promedio_cliente, String promedio_dueno) {
		super();
		this.promedio_cliente = promedio_cliente;
		this.promedio_dueno = promedio_dueno;
	}

	public String getPromedio_cliente() {
		return promedio_cliente;
	}

	public void setPromedio_cliente(String promedio_cliente) {
		this.promedio_cliente = promedio_cliente;
	}

	public String getPromedio_dueno() {
		return promedio_dueno;
	}

	public void setPromedio_dueno(String promedio_dueno) {
		this.promedio_dueno = promedio_dueno;
	}

	@Override
	public String toString() {
		return "Calificacion [promedio_cliente=" + promedio_cliente + ", promedio_dueno=" + promedio_dueno + "]";
	}
	
}
