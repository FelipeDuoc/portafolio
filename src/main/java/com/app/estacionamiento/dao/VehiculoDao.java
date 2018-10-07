package com.app.estacionamiento.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.app.estacionamiento.domain.Vehiculo;

@Component
public interface VehiculoDao {
	public List<Vehiculo> getAllVehicles(int id_usuario);
	public int createVehicle(Vehiculo vehiculo, int idPersona);
	public Vehiculo getVehiculexId(int idPersona, int idVehiculo);
	public int updateVehicle(Vehiculo vehiculo);
	public int deleteVehicle(int idVehiculo);
	public void setDataSource(DataSource ds);
}
