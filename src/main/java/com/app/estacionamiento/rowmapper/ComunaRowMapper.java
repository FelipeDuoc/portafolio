package com.app.estacionamiento.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.Comuna;

public class ComunaRowMapper implements RowMapper<Comuna>{

	@Override
	public Comuna mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comuna c = new Comuna();
		c.setNombreComuna(rs.getString("NOMBRE_COMUNA"));
		return c;
	}

}
