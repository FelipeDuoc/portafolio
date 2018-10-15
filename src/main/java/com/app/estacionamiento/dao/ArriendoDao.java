package com.app.estacionamiento.dao;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.app.estacionamiento.domain.Arriendo;

@Component
public interface ArriendoDao {
	public void setDataSource(DataSource ds);
	public Arriendo newArriendo(Arriendo arriendo);
	public Arriendo arriendoActivo(Integer idPersona);
	
}
