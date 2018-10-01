package com.app.estacionamiento.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.CredencialesResp;

public class CredencialesRespRowMapper implements RowMapper<CredencialesResp> {

	@Override
	public CredencialesResp mapRow(ResultSet rs, int rowNum) throws SQLException {
		CredencialesResp cr = new CredencialesResp();
		cr.setIdUsuario(rs.getString("ID_USUARIO"));
		cr.setIdPersona(rs.getString("ID_PERSONA"));
		cr.setIdRol(rs.getString("ID_ROL"));
		cr.setNombrePersona(rs.getString("NOMBRE_PERSONA"));
		
		return cr;
		
	}

}
