package com.app.estacionamiento.domain;

public class TipoCuentaBanco {
	private Integer idTipoCuentaBanco;
	private String descripcion;
	
	public TipoCuentaBanco(Integer idTipoCuentaBanco, String descripcion) {
		super();
		this.idTipoCuentaBanco = idTipoCuentaBanco;
		this.descripcion = descripcion;
	}

	public TipoCuentaBanco() {
		super();
	}

	public Integer getIdTipoCuentaBanco() {
		return idTipoCuentaBanco;
	}

	public void setIdTipoCuentaBanco(Integer idTipoCuentaBanco) {
		this.idTipoCuentaBanco = idTipoCuentaBanco;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoCuentaBanco [idTipoCuentaBanco=" + idTipoCuentaBanco + ", descripcion=" + descripcion + "]";
	}
	
	
}
