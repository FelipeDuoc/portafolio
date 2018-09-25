package com.app.estacionamiento.domain;

public class Test {
	
	private String campo1;
	private String campo2;
	private String descripcion;

	public Test() {
		super();
	}
	public Test(String campo1, String campo2, String descripcion) {
		super();
		this.campo1 = campo1;
		this.campo2 = campo2;
		this.descripcion = descripcion;
	}
	public String getCampo1() {
		return campo1;
	}
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}
	public String getCampo2() {
		return campo2;
	}
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
