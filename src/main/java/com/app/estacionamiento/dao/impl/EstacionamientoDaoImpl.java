package com.app.estacionamiento.dao.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.app.estacionamiento.dao.EstacionamientoDao;
import com.app.estacionamiento.domain.Estacionamiento;
import com.app.estacionamiento.rowmapper.VehiculoRowMapper;

import oracle.jdbc.OracleTypes;

@Component
public class EstacionamientoDaoImpl implements EstacionamientoDao{

	@Autowired
	DataSource datasource;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Estacionamiento> getAllParking(int idPersona) {
		List<Estacionamiento> lista = null;
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ESTACIONAMIENTO.PROC_SELECT_ESTACIONAMIENTO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("in_id_persona",OracleTypes.INTEGER),
										new SqlOutParameter("o_sql_code", OracleTypes.VARCHAR),
										new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new VehiculoRowMapper()),
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(idPersona);
		lista = (List<Estacionamiento>) result.get("PCURSOR");
		
		return lista;
	}

	@Override
	public void setDataSource(DataSource ds) {
		
	}

}
