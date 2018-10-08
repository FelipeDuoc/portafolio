package com.app.estacionamiento.domain;

public class EstacionamientoObjBD {
	private int idEstacionamiento;
	private String descripcion;
	private String latitud;
	private String longitud;
	private String nombreCalle;
	private String numeroCalle;
	private int disponibilidad;
	private String observacion;
	private int idComuna;
	private String nombreComuna;
	private int idEstado;
	private int idPersona;
	private int idTarifa;
	private int valorTarifa;
	private String nombreDueno;
	private String descripcionTipoTarifa;
	
	
	public EstacionamientoObjBD() {
		super();
	}
	

	

	public EstacionamientoObjBD(int idEstacionamiento, String descripcion, String latitud, String longitud,
			String nombreCalle, String numeroCalle, int disponibilidad, String observacion, int idComuna,
			String nombreComuna, int idEstado, int idPersona, int idTarifa, int valorTarifa, String nombreDueno,
			String descripcionTipoTarifa) {
		super();
		this.idEstacionamiento = idEstacionamiento;
		this.descripcion = descripcion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.nombreCalle = nombreCalle;
		this.numeroCalle = numeroCalle;
		this.disponibilidad = disponibilidad;
		this.observacion = observacion;
		this.idComuna = idComuna;
		this.nombreComuna = nombreComuna;
		this.idEstado = idEstado;
		this.idPersona = idPersona;
		this.idTarifa = idTarifa;
		this.valorTarifa = valorTarifa;
		this.nombreDueno = nombreDueno;
		this.descripcionTipoTarifa = descripcionTipoTarifa;
	}




	public String getNombreDueno() {
		return nombreDueno;
	}




	public void setNombreDueno(String nombreDueno) {
		this.nombreDueno = nombreDueno;
	}




	public String getDescripcionTipoTarifa() {
		return descripcionTipoTarifa;
	}




	public void setDescripcionTipoTarifa(String descripcionTipoTarifa) {
		this.descripcionTipoTarifa = descripcionTipoTarifa;
	}




	public String getNombreComuna() {
		return nombreComuna;
	}



	public void setNombreComuna(String nombreComuna) {
		this.nombreComuna = nombreComuna;
	}



	public int getValorTarifa() {
		return valorTarifa;
	}



	public void setValorTarifa(int valorTarifa) {
		this.valorTarifa = valorTarifa;
	}



	public int getIdEstacionamiento() {
		return idEstacionamiento;
	}

	public void setIdEstacionamiento(int idEstacionamiento) {
		this.idEstacionamiento = idEstacionamiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getNombreCalle() {
		return nombreCalle;
	}

	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}

	public String getNumeroCalle() {
		return numeroCalle;
	}

	public void setNumeroCalle(String numeroCalle) {
		this.numeroCalle = numeroCalle;
	}

	public int getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(int disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getIdComuna() {
		return idComuna;
	}

	public void setIdComuna(int idComuna) {
		this.idComuna = idComuna;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdTarifa() {
		return idTarifa;
	}

	public void setIdTarifa(int idTarifa) {
		this.idTarifa = idTarifa;
	}




	@Override
	public String toString() {
		return "EstacionamientoObjBD [idEstacionamiento=" + idEstacionamiento + ", descripcion=" + descripcion
				+ ", latitud=" + latitud + ", longitud=" + longitud + ", nombreCalle=" + nombreCalle + ", numeroCalle="
				+ numeroCalle + ", disponibilidad=" + disponibilidad + ", observacion=" + observacion + ", idComuna="
				+ idComuna + ", nombreComuna=" + nombreComuna + ", idEstado=" + idEstado + ", idPersona=" + idPersona
				+ ", idTarifa=" + idTarifa + ", valorTarifa=" + valorTarifa + ", nombreDueno=" + nombreDueno
				+ ", descripcionTipoTarifa=" + descripcionTipoTarifa + "]";
	}

	
	
	
	
}
