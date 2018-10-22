package com.app.estacionamiento.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.Banco;

public class BancoRowMapper implements RowMapper<Banco>{

	@Override
	public Banco mapRow(ResultSet rs, int rowNum) throws SQLException {
		Banco banco = new Banco();
		banco.setIdBanco(rs.getInt("ID_BANCO"));
		banco.setDescripcionBanco(rs.getString("DESCRIPCION"));
		return banco;
	}

}
