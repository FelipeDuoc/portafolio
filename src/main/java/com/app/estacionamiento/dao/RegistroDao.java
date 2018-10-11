package com.app.estacionamiento.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.app.estacionamiento.domain.Registro;

@Component
public interface RegistroDao {
	public int registerAccount(Registro registro);
	public void setDataSource(DataSource ds);
}
