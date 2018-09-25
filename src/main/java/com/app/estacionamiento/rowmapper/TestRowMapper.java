package com.app.estacionamiento.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.Test;

public class TestRowMapper implements RowMapper<Test>{

	@Override
	public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
		Test ts = new Test();
		ts.setCampo1(rs.getString("CAMPO1"));
		ts.setCampo2(rs.getString("CAMPO2"));
		ts.setDescripcion(rs.getString("DESCRIPCION"));
		return ts;
	}

}
