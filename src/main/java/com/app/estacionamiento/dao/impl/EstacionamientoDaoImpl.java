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
import com.app.estacionamiento.domain.EstacionamientoObjBD;
import com.app.estacionamiento.rowmapper.EstacionamientoRowMapper;

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
										new SqlOutParameter("PCURSOR",OracleTypes.CURSOR, new EstacionamientoRowMapper()),
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

	@Override
	public int createParking(EstacionamientoObjBD estacionamiento, int idPersona) {
		int res=0;
		
		StoredProcedure procedure = new GenericStoredProcedure();
		procedure.setDataSource(datasource);
		procedure.setSql("PKG_ESTACIONAMIENTO.PROC_INSERT_ESTACIONAMIENTO");
		procedure.setFunction(false);
		
		SqlParameter[] parameters = {	new SqlParameter("IN_DESCRIPCION",OracleTypes.VARCHAR),
										new SqlParameter("IN_LATITUD",OracleTypes.VARCHAR),
										new SqlParameter("IN_LONGITUD",OracleTypes.VARCHAR),
										new SqlParameter("IN_NOMBRE_CALLE",OracleTypes.VARCHAR),
										new SqlParameter("IN_NUMERO_CALLE",OracleTypes.VARCHAR),
										new SqlParameter("IN_OBSERVACION",OracleTypes.VARCHAR),
										new SqlParameter("IN_COMUNA",OracleTypes.VARCHAR),
										new SqlParameter("IN_ID_PERSONA",OracleTypes.INTEGER),
										new SqlParameter("IN_VALOR_TARIFA",OracleTypes.INTEGER),
										
										new SqlOutParameter("O_RESULT", OracleTypes.INTEGER)
									 };
		
		procedure.setParameters(parameters);
		procedure.compile();
		Map<String, Object>  result = procedure.execute(estacionamiento.getDescripcion(),
														estacionamiento.getLatitud(),
														estacionamiento.getLongitud(),
														estacionamiento.getNombreCalle(),
														estacionamiento.getNumeroCalle(),
														estacionamiento.getObservacion(),
														estacionamiento.getNombreComuna(),
														idPersona,
														estacionamiento.getValorTarifa());
		res = (int) result.get("O_RESULT");
		
		return res;
	}

}
