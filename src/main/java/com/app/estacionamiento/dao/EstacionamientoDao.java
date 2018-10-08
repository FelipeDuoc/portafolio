package com.app.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.app.estacionamiento.domain.Estacionamiento;
import com.app.estacionamiento.domain.EstacionamientoObjBD;

@Component
public interface EstacionamientoDao {
	public List<Estacionamiento> getAllParking(int idPersona);
	public int createParking(EstacionamientoObjBD estacionamiento, int idPersona);
	public void setDataSource(DataSource ds);
}
