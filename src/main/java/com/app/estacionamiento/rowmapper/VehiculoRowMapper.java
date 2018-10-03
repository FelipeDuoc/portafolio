package com.app.estacionamiento.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.Vehiculo;

public class VehiculoRowMapper implements RowMapper<Vehiculo> {

	@Override
	public Vehiculo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Vehiculo ve = new Vehiculo();
		ve.setIdVehiculo(rs.getInt("ID_VEHICULO"));
		ve.setPatente(rs.getString("PATENTE"));
		ve.setDescripcion(rs.getString("DESCRIPCION"));
		return ve;
	}

}
