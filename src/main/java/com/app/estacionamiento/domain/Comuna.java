package com.app.estacionamiento.domain;

public class Comuna {
	private String nombreComuna;

	public Comuna(String nombreComuna) {
		super();
		this.nombreComuna = nombreComuna;
	}

	public Comuna() {
		super();
	}

	public String getNombreComuna() {
		return nombreComuna;
	}

	public void setNombreComuna(String nombreComuna) {
		this.nombreComuna = nombreComuna;
	}

	@Override
	public String toString() {
		return "Comuna [nombreComuna=" + nombreComuna + "]";
	}
	
	
	
}
