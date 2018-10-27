package com.app.estacionamiento.domain;

public class Registro {
	private Integer idPersona;
	private String rut;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String nombreCalle;
	private String numeroCalle;
	private String telefono;
	private String email;
	
	private Integer idTarjeta;
	private String numeroTarjeta;
	private String codigoSeguridadTarjeta;
	private String fechaVencimiento;
	
	private String idUsuario;
	private String usuario;
	private String contrasena;
	private Integer idRol;
	
	private String numeroCuentaDeposito;
	private String idBanco;
	private String bancoDescripcion;
	private String idTipoCuenta;
	private String tipoCuentaDescripcion;
	
	
	public Registro() {
		super();
	}
	
	

	public Registro(Integer idPersona, String rut, String nombre, String apellidoPaterno, String apellidoMaterno,
			String nombreCalle, String numeroCalle, String telefono, String email, Integer idTarjeta,
			String numeroTarjeta, String codigoSeguridadTarjeta, String fechaVencimiento, String idUsuario,
			String usuario, String contrasena, Integer idRol, String numeroCuentaDeposito, String idBanco,
			String bancoDescripcion, String idTipoCuenta, String tipoCuentaDescripcion) {
		super();
		this.idPersona = idPersona;
		this.rut = rut;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.nombreCalle = nombreCalle;
		this.numeroCalle = numeroCalle;
		this.telefono = telefono;
		this.email = email;
		this.idTarjeta = idTarjeta;
		this.numeroTarjeta = numeroTarjeta;
		this.codigoSeguridadTarjeta = codigoSeguridadTarjeta;
		this.fechaVencimiento = fechaVencimiento;
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.idRol = idRol;
		this.numeroCuentaDeposito = numeroCuentaDeposito;
		this.idBanco = idBanco;
		this.bancoDescripcion = bancoDescripcion;
		this.idTipoCuenta = idTipoCuenta;
		this.tipoCuentaDescripcion = tipoCuentaDescripcion;
	}



	public String getNumeroCuentaDeposito() {
		return numeroCuentaDeposito;
	}



	public void setNumeroCuentaDeposito(String numeroCuentaDeposito) {
		this.numeroCuentaDeposito = numeroCuentaDeposito;
	}



	public String getIdBanco() {
		return idBanco;
	}



	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
	}



	public String getBancoDescripcion() {
		return bancoDescripcion;
	}



	public void setBancoDescripcion(String bancoDescripcion) {
		this.bancoDescripcion = bancoDescripcion;
	}



	public String getIdTipoCuenta() {
		return idTipoCuenta;
	}



	public void setIdTipoCuenta(String idTipoCuenta) {
		this.idTipoCuenta = idTipoCuenta;
	}



	public String getTipoCuentaDescripcion() {
		return tipoCuentaDescripcion;
	}



	public void setTipoCuentaDescripcion(String tipoCuentaDescripcion) {
		this.tipoCuentaDescripcion = tipoCuentaDescripcion;
	}



	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(Integer idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getCodigoSeguridadTarjeta() {
		return codigoSeguridadTarjeta;
	}

	public void setCodigoSeguridadTarjeta(String codigoSeguridadTarjeta) {
		this.codigoSeguridadTarjeta = codigoSeguridadTarjeta;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Registro [idPersona=" + idPersona + ", rut=" + rut + ", nombre=" + nombre + ", apellidoPaterno="
				+ apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", nombreCalle=" + nombreCalle
				+ ", numeroCalle=" + numeroCalle + ", telefono=" + telefono + ", email=" + email + ", idTarjeta="
				+ idTarjeta + ", numeroTarjeta=" + numeroTarjeta + ", codigoSeguridadTarjeta=" + codigoSeguridadTarjeta
				+ ", fechaVencimiento=" + fechaVencimiento + ", idUsuario=" + idUsuario + ", usuario=" + usuario
				+ ", contrasena=" + contrasena + ", idRol=" + idRol + ", numeroCuentaDeposito=" + numeroCuentaDeposito
				+ ", idBanco=" + idBanco + ", bancoDescripcion=" + bancoDescripcion + ", idTipoCuenta=" + idTipoCuenta
				+ ", tipoCuentaDescripcion=" + tipoCuentaDescripcion + "]";
	}

	
	
	
	
	
	
	
	
	
}
