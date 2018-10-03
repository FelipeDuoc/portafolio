package com.app.estacionamiento.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.estacionamiento.dao.VehiculoDao;
import com.app.estacionamiento.domain.Vehiculo;
import com.app.estacionamiento.rowmapper.VehiculoRowMapper;

import oracle.jdbc.OracleTypes;

@Component
public class VehiculoDaoImpl implements VehiculoDao{

	@Autowired
	DataSource datasource;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> getAllVehicles(int id_usuario) {
		List<Vehiculo> resp= null;
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_VEHICULO.PROC_SELECT_VEHICULOS");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("in_id_persona",OracleTypes.INTEGER),
										new SqlOutParameter("o_sql_code", OracleTypes.VARCHAR),
										new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new VehiculoRowMapper()),
									 };
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(id_usuario);
		resp = (ArrayList<Vehiculo>) result.get("PCURSOR");
		return resp;
	}

	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}
	
}
