package com.app.estacionamiento.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.EstacionamientoObjBD;

public class EstacionamientosEnUsoRowMapper implements RowMapper<EstacionamientoObjBD> {

	@Override
	public EstacionamientoObjBD mapRow(ResultSet rs, int rowNum) throws SQLException {
		EstacionamientoObjBD est = new EstacionamientoObjBD();
		est.setNombreDueno(rs.getString("NOMBRE_DUENO_VEHICULO"));
		est.setObservacion(rs.getString("PATENTE"));
		est.setDescripcion(rs.getString("DESCRIPCION"));
		est.setIdTarifa(rs.getInt("CANTIDAD_HORAS"));
		est.setDescripcionTipoTarifa(rs.getString("VALIDA_10_HORAS"));
		return est;
	}

}
