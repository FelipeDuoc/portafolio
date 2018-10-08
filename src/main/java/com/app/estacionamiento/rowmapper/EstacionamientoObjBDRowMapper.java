package com.app.estacionamiento.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.EstacionamientoObjBD;

public class EstacionamientoObjBDRowMapper implements RowMapper<EstacionamientoObjBD>{

	@Override
	public EstacionamientoObjBD mapRow(ResultSet rs, int rowNum) throws SQLException {
		EstacionamientoObjBD est = new EstacionamientoObjBD();
		est.setIdEstacionamiento(rs.getInt("ID_ESTACIONAMIENTO"));
		est.setDescripcion(rs.getString("DESCRIPCION"));
		est.setLatitud(rs.getString("LATITUD"));
		est.setLongitud(rs.getString("LONGITUD"));
		est.setNombreCalle(rs.getString("NOMBRE_CALLE"));
		est.setNumeroCalle(rs.getString("NUMERO_CALLE"));
		est.setDisponibilidad(rs.getInt("DISPONIBILIDAD"));
		est.setObservacion(rs.getString("OBSERVACION"));
		est.setNombreComuna(rs.getString("NOMBRE_COMUNA"));
		est.setIdEstado(rs.getInt("ESTADO_ID_ESTADO"));
		est.setIdPersona(rs.getInt("PERSONA_ID_PERSONA"));
		est.setNombreDueno(rs.getString("NOMBRE_DUENO"));
		est.setIdTarifa(rs.getInt("TARIFA_ID_TARIFA"));
		est.setValorTarifa(rs.getInt("TARIFA"));
		est.setDescripcionTipoTarifa(rs.getString("DESCRIPCION_TIPO_TARIFA"));
		return est;
	}

}
