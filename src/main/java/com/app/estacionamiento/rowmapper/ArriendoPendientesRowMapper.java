package com.app.estacionamiento.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.Arriendo;

public class ArriendoPendientesRowMapper implements RowMapper<Arriendo>{

	@Override
	public Arriendo mapRow(ResultSet rs, int rowNum) throws SQLException {
		Arriendo arr = new Arriendo();
		arr.setPatenteVehiculo(rs.getString("PATENTE_VEHICULO"));
		arr.setIdArriendo(rs.getInt("ID_ARRIENDO"));
		arr.setDireccionEstacionamiento(rs.getString("DIRECCION_ESTACIONAMIENTO"));
		arr.setFechaRealSalida(rs.getString("FECHA_ARRIENDO"));
		return arr;
	}

}
