package com.app.estacionamiento.domain;

public class CredencialesResp {
	private String idUsuario;
	private String idPersona;
	private String idRol;
	private String nombrePersona;
	
	public CredencialesResp() {
		super();
	}
	
	

	public CredencialesResp(String idUsuario, String idPersona, String idRol, String nombrePersona) {
		super();
		this.idUsuario = idUsuario;
		this.idPersona = idPersona;
		this.idRol = idRol;
		this.nombrePersona = nombrePersona;
	}



	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	@Override
	public String toString() {
		return "CredencialesResp [idUsuario=" + idUsuario + ", idPersona=" + idPersona + ", idRol=" + idRol
				+ ", nombrePersona=" + nombrePersona + "]";
	}
	
	
}
