package com.app.estacionamiento.dao.impl;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.GenericStoredProcedure;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import com.app.estacionamiento.dao.ArriendoDao;
import com.app.estacionamiento.domain.Arriendo;

import oracle.jdbc.OracleTypes;

@Component
public class ArriendoDaoImpl implements ArriendoDao {

	
	@Autowired
	DataSource datasource;	
	
	
	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Arriendo newArriendo(Arriendo arriendo) {
		Integer res = 0;
		Integer res_total = 0;
		Arriendo arr = null;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ARRIENDO.PROC_INICIO_ARRIENDO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_FECHA_ENTRADA",OracleTypes.TIMESTAMP),
										new SqlParameter("IN_FECHA_SALIDA_ESTIMADA",OracleTypes.TIMESTAMP),
										new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlParameter("IN_ID_ESTACIONAMIENTO",OracleTypes.INTEGER),
										new SqlParameter("IN_ID_VEHICULO",OracleTypes.INTEGER),
										
										new SqlOutParameter("O_TOTAL_TARIFA",OracleTypes.INTEGER),
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(arriendo.getFechaDesde(),
														arriendo.getFechaHasta(),
														arriendo.getIdPersona(),
														arriendo.getIdEstacionamiento(),
														arriendo.getIdVehiculo());
		res = (Integer) result.get("O_RESULT");
		res_total = (Integer) result.get("O_TOTAL_TARIFA");
		
		if(res.equals(1)) {
			arr = new Arriendo();
			arr = arriendo;
			arr.setTotalArriendo(res_total);
			return arr;
		}else {
			return arr;
		}
		
	}

}
