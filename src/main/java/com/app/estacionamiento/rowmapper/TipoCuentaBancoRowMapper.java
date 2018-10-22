package com.app.estacionamiento.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.app.estacionamiento.domain.TipoCuentaBanco;

public class TipoCuentaBancoRowMapper implements RowMapper<TipoCuentaBanco>{

	@Override
	public TipoCuentaBanco mapRow(ResultSet rs, int rowNum) throws SQLException {
		TipoCuentaBanco t = new TipoCuentaBanco();
		t.setIdTipoCuentaBanco(rs.getInt("ID_TIPO_CUENTA"));
		t.setDescripcion(rs.getString("DESCRIPCION_TIPO_CUENTA"));
		return t;
	}

}
