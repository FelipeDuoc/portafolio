package com.app.estacionamiento.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.app.estacionamiento.domain.Registro;
import com.app.estacionamiento.domain.Tarjeta;

@Component
public interface RegistroDao {
	public int registerAccount(Registro registro);
	public void setDataSource(DataSource ds);
	public Registro myAccount(int idPersona, int idUsuario);
	public Integer setCreditCard(Integer idPersona, Tarjeta tarjeta);
	public Integer setMyAccount(Integer idPersona, Integer idUsuario, Registro registro);
	public Boolean checkCreditCard(String creditCardNumber);
	public Integer setTransferCard(Integer idPersona, String numerocuenta, Integer idBanco, Integer idTipoCuenta);
}
