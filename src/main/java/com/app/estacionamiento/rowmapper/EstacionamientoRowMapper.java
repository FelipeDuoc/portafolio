package com.app.estacionamiento.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.Estacionamiento;

public class EstacionamientoRowMapper implements RowMapper<Estacionamiento>{

	@Override
	public Estacionamiento mapRow(ResultSet rs, int rowNum) throws SQLException {
		Estacionamiento est = new Estacionamiento();
		est.setIdEstacionamiento(rs.getInt("ID_ESTACIONAMIENTO"));
		est.setDescripcion(rs.getString("DESCRIPCION"));
		est.setTarifa(rs.getString("TIPO_TARIFA"));
		return est;
	}

}
