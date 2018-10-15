package com.app.estacionamiento.domain;

public class Arriendo {
	private Integer idEstacionamiento;
	private Integer idPersona;
	private Integer idVehiculo;
	private String fechaDesde;
	private String fechaHasta;
	private String fechaRealSalida;
	private Integer totalArriendo;
	
	public Arriendo() {
		super();
	}
	public Arriendo(Integer idEstacionamiento, Integer idPersona, Integer idVehiculo, String fechaDesde,
			String fechaHasta, String fechaRealSalida, Integer totalArriendo) {
		super();
		this.idEstacionamiento = idEstacionamiento;
		this.idPersona = idPersona;
		this.idVehiculo = idVehiculo;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.fechaRealSalida = fechaRealSalida;
		this.totalArriendo = totalArriendo;
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
		return "Arriendo [idEstacionamiento=" + idEstacionamiento + ", idPersona=" + idPersona + ", idVehiculo="
				+ idVehiculo + ", fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", fechaRealSalida="
				+ fechaRealSalida + ", totalArriendo=" + totalArriendo + "]";
	}
	
	
	
	
}
