package com.app.estacionamiento.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.Arriendo;

public class ArriendoRowMapper implements RowMapper<Arriendo>{

	@Override
	public Arriendo mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Arriendo arr = new Arriendo();
		arr.setIdArriendo(rs.getInt("ID_ARRIENDO"));
		arr.setFechaDesde(rs.getString("FECHA_ENTRADA"));
		arr.setFechaRealSalida(rs.getString("FECHA_SALIDA_REAL"));
		//arr.setValorTarifa(rs.getString("TARIFA"));
		arr.setTotalPagoExtra(rs.getInt("TARIFA"));
		arr.setTotalArriendo(rs.getInt("VALOR_TOTAL_ARRIENDO"));
		arr.setIdEstacionamiento(rs.getInt("ID_ESTACIONAMIENTO"));
		arr.setDireccionEstacionamiento(rs.getString("ESTACIONAMIENTO"));
		arr.setNombreDuenoEstacionamiento(rs.getString("NOMBRE"));
		arr.setPatenteVehiculo(rs.getString("PATENTE"));
		return arr;
	}

}
