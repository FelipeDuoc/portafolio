package com.app.estacionamiento.domain;

public class Arriendo {
	private Integer idArriendo;
	private Integer idEstacionamiento;
	private Integer idPersona;
	private Integer idVehiculo;
	private String patenteVehiculo;
	private String nombreDuenoEstacionamiento;
	private String telefonoDueno;
	private String direccionEstacionamiento;
	private String fechaDesde;
	private String fechaHasta;
	private String fechaRealSalida;
	private Integer totalArriendo;
	
	public Arriendo() {
		super();
	}
	
	
	
	public Arriendo(Integer idEstacionamiento, Integer idPersona, Integer idVehiculo, String patenteVehiculo,
			String nombreDuenoEstacionamiento, String telefonoDueno, String direccionEstacionamiento, String fechaDesde,
			String fechaHasta, String fechaRealSalida, Integer totalArriendo, Integer idArriendo) {
		super();
		this.idEstacionamiento = idEstacionamiento;
		this.idPersona = idPersona;
		this.idVehiculo = idVehiculo;
		this.patenteVehiculo = patenteVehiculo;
		this.nombreDuenoEstacionamiento = nombreDuenoEstacionamiento;
		this.telefonoDueno = telefonoDueno;
		this.direccionEstacionamiento = direccionEstacionamiento;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.fechaRealSalida = fechaRealSalida;
		this.totalArriendo = totalArriendo;
		this.idArriendo = idArriendo;
	}



	public Integer getIdArriendo() {
		return idArriendo;
	}



	public void setIdArriendo(Integer idArriendo) {
		this.idArriendo = idArriendo;
	}



	public Integer getIdEstacionamiento() {
		return idEstacionamiento;
	}



	public void setIdEstacionamiento(Integer idEstacionamiento) {
		this.idEstacionamiento = idEstacionamiento;
	}



	public Integer getIdPersona() {
		return idPersona;
	}



	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}



	public Integer getIdVehiculo() {
		return idVehiculo;
	}



	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}



	public String getPatenteVehiculo() {
		return patenteVehiculo;
	}



	public void setPatenteVehiculo(String patenteVehiculo) {
		this.patenteVehiculo = patenteVehiculo;
	}



	public String getNombreDuenoEstacionamiento() {
		return nombreDuenoEstacionamiento;
	}



	public void setNombreDuenoEstacionamiento(String nombreDuenoEstacionamiento) {
		this.nombreDuenoEstacionamiento = nombreDuenoEstacionamiento;
	}



	public String getTelefonoDueno() {
		return telefonoDueno;
	}



	public void setTelefonoDueno(String telefonoDueno) {
		this.telefonoDueno = telefonoDueno;
	}



	public String getDireccionEstacionamiento() {
		return direccionEstacionamiento;
	}



	public void setDireccionEstacionamiento(String direccionEstacionamiento) {
		this.direccionEstacionamiento = direccionEstacionamiento;
	}



	public String getFechaDesde() {
		return fechaDesde;
	}



	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}



	public String getFechaHasta() {
		return fechaHasta;
	}



	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}



	public String getFechaRealSalida() {
		return fechaRealSalida;
	}



	public void setFechaRealSalida(String fechaRealSalida) {
		this.fechaRealSalida = fechaRealSalida;
	}



	public Integer getTotalArriendo() {
		return totalArriendo;
	}



	public void setTotalArriendo(Integer totalArriendo) {
		this.totalArriendo = totalArriendo;
	}



	@Override
	public String toString() {
		return "Arriendo [idArriendo=" + idArriendo + ", idEstacionamiento=" + idEstacionamiento + ", idPersona="
				+ idPersona + ", idVehiculo=" + idVehiculo + ", patenteVehiculo=" + patenteVehiculo
				+ ", nombreDuenoEstacionamiento=" + nombreDuenoEstacionamiento + ", telefonoDueno=" + telefonoDueno
				+ ", direccionEstacionamiento=" + direccionEstacionamiento + ", fechaDesde=" + fechaDesde
				+ ", fechaHasta=" + fechaHasta + ", fechaRealSalida=" + fechaRealSalida + ", totalArriendo="
				+ totalArriendo + "]";
	}

	

	
	
	
	
	
}
