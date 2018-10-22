package com.app.estacionamiento.domain;

public class Banco {
	private Integer idBanco;
	private String descripcionBanco;

	public Banco(Integer idBanco, String descripcionBanco) {
		super();
		this.idBanco = idBanco;
		this.descripcionBanco = descripcionBanco;
	}

	public Banco() {
		super();
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public String getDescripcionBanco() {
		return descripcionBanco;
	}

	public void setDescripcionBanco(String descripcionBanco) {
		this.descripcionBanco = descripcionBanco;
	}

	@Override
	public String toString() {
		return "Banco [idBanco=" + idBanco + ", descripcionBanco=" + descripcionBanco + "]";
	}
	
	
	
	
}
