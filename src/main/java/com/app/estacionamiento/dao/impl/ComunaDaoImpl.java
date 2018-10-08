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

import com.app.estacionamiento.dao.ComunaDao;
import com.app.estacionamiento.domain.Comuna;
import com.app.estacionamiento.rowmapper.ComunaRowMapper;

import oracle.jdbc.OracleTypes;

@Component
public class ComunaDaoImpl implements ComunaDao{

	@Autowired
	DataSource datasource;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Comuna> getAllComunes() {
		List<Comuna> lista = null;
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_COMUNAS.PROC_LISTAR_COMUNAS");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new ComunaRowMapper())};
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute();
		lista = (List<Comuna>) result.get("PCURSOR");
		
		return lista;
		
	}

	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
	}

}
