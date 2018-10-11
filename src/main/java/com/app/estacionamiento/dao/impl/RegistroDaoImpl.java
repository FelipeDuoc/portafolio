package com.app.estacionamiento.dao.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.app.estacionamiento.dao.RegistroDao;
import com.app.estacionamiento.domain.Registro;

import oracle.jdbc.OracleTypes;

@Component
public class RegistroDaoImpl implements RegistroDao{

	@Autowired
	DataSource datasource;
	
	@Override
	public int registerAccount(Registro registro) {
		int res=0;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_REGISTRO.PROC_REGISTRO_CLIENTE");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_RUT_PERSONA",OracleTypes.VARCHAR),
										new SqlParameter("IN_NOMBRES_PERSONA",OracleTypes.VARCHAR),
										new SqlParameter("IN_APELLIDO_PATERNO",OracleTypes.VARCHAR),
										new SqlParameter("IN_APELLIDO_MATERNO",OracleTypes.VARCHAR),
										new SqlParameter("IN_NOMBRE_CALLE",OracleTypes.VARCHAR),
										new SqlParameter("IN_NUMERO_CAllE",OracleTypes.VARCHAR),
										new SqlParameter("IN_TELEFONO",OracleTypes.VARCHAR),
										new SqlParameter("IN_EMAIL",OracleTypes.VARCHAR),
										new SqlParameter("IN_NUMERO_TARJETA",OracleTypes.VARCHAR),
										new SqlParameter("IN_CODIGO_SEGURIDAD_TARJETA",OracleTypes.VARCHAR),
										new SqlParameter("IN_FECHA_VENCIMIENTO_TARJETA",OracleTypes.VARCHAR),
										new SqlParameter("IN_NOMBRE_USUARIO",OracleTypes.VARCHAR),
										new SqlParameter("IN_PASSWORD_USUARIO",OracleTypes.VARCHAR),
										new SqlParameter("IN_ID_ROL",OracleTypes.INTEGER),
										
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(registro.getRut(),
														registro.getNombre(),
														registro.getApellidoPaterno(),
														registro.getApellidoMaterno(),
														registro.getNombreCalle(),
														registro.getNumeroCalle(),
														registro.getTelefono(),
														registro.getEmail(),
														registro.getNumeroTarjeta(),
														registro.getCodigoSeguridadTarjeta(),
														registro.getFechaVencimiento(),
														registro.getUsuario(),
														registro.getContrasena(),
														registro.getIdRol());
		res = (int) result.get("O_RESULT");
		
		return res;
	}

	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

}
