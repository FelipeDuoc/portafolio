package com.app.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.app.estacionamiento.domain.Vehiculo;

@Component
public interface VehiculoDao {
	public List<Vehiculo> getAllVehicles(int id_usuario);
	public void setDataSource(DataSource ds);
}
