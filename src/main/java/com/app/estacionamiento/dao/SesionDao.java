package com.app.estacionamiento.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.app.estacionamiento.domain.Credenciales;
import com.app.estacionamiento.domain.CredencialesResp;

@Component
public interface SesionDao {
	public CredencialesResp inicioSesion(Credenciales credenciales);
	public void setDataSource(DataSource ds);
}
